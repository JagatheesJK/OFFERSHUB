package com.hub.offershub.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityPaymentConfirmBinding;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.Constants;
import com.hub.offershub.utils.Utils;
import com.razorpay.Checkout;
import com.razorpay.Order;
import com.razorpay.PaymentResultListener;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

public class PaymentConfirmActivity extends BaseActivity implements View.OnClickListener, PaymentResultListener {

    private ActivityPaymentConfirmBinding binding;
    private SubscriptionPackageResponse.SubscriptionPackage model;
    public String mobileRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setListener();

        binding.waitingCard.setVisibility(View.VISIBLE);

        GenerateOrder generateOrder = new GenerateOrder(model);
        generateOrder.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "order_generation");
    }

    private void init() {
        mobileRandom = "6"+generateRandomNumbers(9);
        model = getIntent().getParcelableExtra("model");
    }

    private void initUI(String transID) {
        binding.transactionTxt.setText(""+transID);
        binding.dateTxt.setText(""+ Utils.milliToDate(System.currentTimeMillis()));
    }

    private void setListener() {
        binding.doneBtn.setOnClickListener(this);
    }

    public String generateRandomNumbers(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    @Override
    public void onPaymentSuccess(String s) {
        isPaymentSuccess = true;
        Log.e("Check_JKPayment", "onPaymentSuccess s : "+s);
        initUI(s);
        binding.successCard.setVisibility(View.VISIBLE);
        binding.waitingCard.setVisibility(View.GONE);
    }

    @Override
    public void onPaymentError(int i, String s) {
        isPaymentSuccess = false;
        try {
            JSONObject root = new JSONObject(s);
            JSONObject results = root.getJSONObject("error");
            Toast.makeText(this, "" + results.getString("reason"), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }

        binding.successCard.setVisibility(View.GONE);
        binding.waitingCard.setVisibility(View.VISIBLE);
        Log.e("Check_JKPayment", "onPaymentError s : "+s+" i : "+i);
        new Handler().postDelayed(() -> {
            finish();
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doneBtn:
                Intent intent = new Intent(PaymentConfirmActivity.this, TestMainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    public class GenerateOrder extends AsyncTask<String, Void, String> {
        SubscriptionPackageResponse.SubscriptionPackage model;
        String orderId;

        public GenerateOrder(SubscriptionPackageResponse.SubscriptionPackage model) {
            this.model = model;
            orderId = "order_" + generateRandomId(14);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress("Please wait...");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject orderRequest = new JSONObject();
                orderRequest.put("amount", (model.price * 100));
                orderRequest.put("currency", "INR");
                orderRequest.put("receipt", orderId);
                orderRequest.put("payment_capture", true);

                RazorpayClient razorpayClient = new RazorpayClient(Constants.razorpay_key_id
                        , Constants.razorpay_key_secret);
                Order order = razorpayClient.Orders.create(orderRequest);
                return order.get("id");
            } catch (RazorpayException e){
                FirebaseCrashlytics.getInstance().recordException(e);
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                setResultAndFinish(false);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String orderId) {
            super.onPostExecute(orderId);
            hideProgress();
            if(orderId!=null) {
                initializeRazorPay(model, orderId, "upi");
            }
        }
    }

    public String generateRandomId(int MAX_LENGTH) {
        return RandomStringUtils.randomAlphanumeric(MAX_LENGTH).toUpperCase();
    }

    private void initializeRazorPay(SubscriptionPackageResponse.SubscriptionPackage model, String orderId, String method) {
        Checkout checkout = new Checkout();
        checkout.setKeyID(Constants.razorpay_key_id);
        checkout.setImage(R.drawable.def_logo);
        Checkout.preload(this);
        //double quotes added for crash fixed
        Log.e("Order Id:", ""+orderId);
        try {
            JSONObject notes = new JSONObject();
            notes.put("user_id", AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID));
            notes.put("plan_id", model.id);

            JSONObject options = new JSONObject();
            options.put(Constants.RAZORPAY_NAME, getString(R.string.app_name));
            options.put(Constants.RAZORPAY_DECRIPTION, model.packageName);
            options.put(Constants.RAZORPAY_IMAGE, "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put(Constants.RAZORPAY_ORDER_ID, orderId);
            options.put(Constants.RAZORPAY_THEME, R.color.colorPrimary);
            options.put(Constants.RAZORPAY_CURRENCY, "INR");
            options.put(Constants.RAZORPAY_AMOUNT, model.price);
            options.put(Constants.RAZORPAY_METHOD, method);
            options.put("notes", notes);
            if (!method.equals(Constants.RAZORPAY_WALLET) && !method.equals(Constants.RAZORPAY_NETBANK)) {
                options.put(Constants.RAZORPAY_EMAIL, mobileRandom+"@gmail.com");
                options.put(Constants.RAZORPAY_CONTACT, mobileRandom);
            }
            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
            setResultAndFinish(false);
        }
    }

    private void setResultAndFinish(boolean isSuccess) {
        if (!isSuccess)
            finish();
    }

    boolean isPaymentSuccess = false;
    @Override
    public void onBackPressed() {
        if (!isPaymentSuccess)
            super.onBackPressed();
    }
}