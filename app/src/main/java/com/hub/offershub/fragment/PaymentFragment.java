package com.hub.offershub.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.adapter.SubScriptionAdapter;
import com.hub.offershub.base.BaseFragment;
import com.hub.offershub.databinding.FragmentPaymentBinding;
import com.hub.offershub.listener.onPlanSelectListener;
import com.hub.offershub.model.SubscriptionPackageResponse;
import com.hub.offershub.utils.Constants;
import com.hub.offershub.utils.customLinearManager.CustomLinearLayoutManagerWithSmoothScroller;
import com.razorpay.Checkout;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends BaseFragment  implements View.OnClickListener, onPlanSelectListener {

    private FragmentPaymentBinding binding;
    private CustomLinearLayoutManagerWithSmoothScroller linearLayoutManager;
    SubScriptionAdapter adapter;
    public String mobileRandom;

    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(getLayoutInflater());
        init();
        setListener();
        return binding.getRoot();
    }

    private void init() {
        mobileRandom = "6"+generateRandomNumbers(9);
        setUpRecycler();
        commonViewModel.getSubscriptionDetails(myProgressDialog);
        getInActiveData();
    }

    public String generateRandomNumbers(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    private void setListener() {
        binding.continueBtn.setOnClickListener(this);
    }

    private void setUpRecycler() {
        linearLayoutManager = new CustomLinearLayoutManagerWithSmoothScroller(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.planRecycler.setLayoutManager(linearLayoutManager);
        adapter = new SubScriptionAdapter(getActivity(),this, list);
        binding.planRecycler.setAdapter(adapter);
        setNotify();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continueBtn:
                if (adapter != null) {
                    GenerateOrder generateOrder = new GenerateOrder(adapter.getSelectedModel());
                    generateOrder.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "order_generation");
                }
                break;
            default:
                break;
        }
    }

    private void setNotify() {
        binding.planRecycler.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }

    private List<SubscriptionPackageResponse.SubscriptionPackage> list = new ArrayList<>();
    private void getInActiveData() {
        commonViewModel.getMutableSubscriptionData().observe(getViewLifecycleOwner(), subscriptionPackageResponse -> {
            if (PaymentFragment.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (subscriptionPackageResponse != null) {
                    Log.e("Check_JK", "InActiveShopsFrag getInActiveData Status : "+subscriptionPackageResponse.status);
                    if(subscriptionPackageResponse.status.equals("success")) {
                        if (subscriptionPackageResponse.data != null) {
                           // binding.empty.emptyConstraint.setVisibility(View.GONE);
                            binding.planRecycler.setVisibility(View.VISIBLE);

                            list.addAll(subscriptionPackageResponse.data);
                            setNotify();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onPlanSelect(SubscriptionPackageResponse.SubscriptionPackage model) {
        /*GenerateOrder generateOrder = new GenerateOrder(model);
        generateOrder.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "order_generation");*/
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
            if(!PaymentFragment.this.isDetached())
                showProgress();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject orderRequest = new JSONObject();
                orderRequest.put("amount", model.price);
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
                //setResultAndFinish(null,null, false, false);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String orderId) {
            super.onPostExecute(orderId);
            if(!PaymentFragment.this.isDetached()) {
                hideProgress();
            }
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
        Checkout.preload(getActivity());
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
            checkout.open(getActivity(), options);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage()+"", Toast.LENGTH_SHORT).show();
            //setResultAndFinish(null,null, false, false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Payment");
    }
}