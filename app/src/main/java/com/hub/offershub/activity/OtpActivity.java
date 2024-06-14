package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityOtpBinding;
import com.hub.offershub.retrofit.API;
import com.hub.offershub.retrofit.RetrofitClient;
import com.hub.offershub.base.Constants;
import com.hub.offershub.utils.Utils;
import com.permissionx.guolindev.PermissionX;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends BaseActivity {
    String name="",mobile="", token = "", otp = "", type = "login";
    boolean isRegister = false;

    Context context;
    private ActivityOtpBinding binding;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getPermission();

        context = this;
        mobile = getIntent().getStringExtra("mobile");
        token = getIntent().getStringExtra("token");
        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
        isRegister = getIntent().getBooleanExtra("isRegister",false);

        binding.resendOtpTxt.setPaintFlags(binding.resendOtpTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        sendOtp("+91" + mobile);
        getLoginCheckData();

        startCountdownTimer();
        binding.pinView.requestFocus();
        binding.verifyBtn.setOnClickListener(v -> {
            String code = binding.pinView.getText().toString();
            if (code.length() < 4) {
                binding.pinView.setError("Enter Valid code");
            } else {
                try {
                    otp = binding.pinView.getText().toString();
                    Log.e("Check_JKNotify", "Verify Btn otp : "+otp);
                    RegisterAPIAsync registerAPIAsync = new RegisterAPIAsync(name, mobile, token, isRegister, otp);
                    registerAPIAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.resendOtpTxt.setOnClickListener(v -> {
            showProgress(getString(R.string.please_wait));
            startCountdownTimer();
            commonViewModel.loginCheck(makeRequest(mobile), myProgressDialog);
        });
    }

    private void sendOtp(String mobile) {
        showProgress(getString(R.string.please_wait));
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            hideProgress();
        }, 2000);
    }

    private void startCountdownTimer() {
        binding.resendOtpTxt.setEnabled(false);
        binding.resendOtpTxt.setAlpha(0.8f);
        binding.resendOtpTimerTxt.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                binding.resendOtpTimerTxt.setText("(" + f.format(sec) + ")");
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                binding.resendOtpTxt.setEnabled(true);
                binding.resendOtpTxt.setAlpha(1f);
                binding.resendOtpTimerTxt.setVisibility(View.GONE);
                cancelCountdownTimer();
            }
        }.start();
    }

    private void cancelCountdownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private Map<String, Object> makeRequest(String mobile) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("mobile", Long.parseLong(mobile));
        requestData.put("type", type);
        requestData.put("device_token", token);
        return requestData;
    }

    public class RegisterAPIAsync extends AsyncTask<String, String, String> {

        String name; String mobile;String device_token; boolean isRegister; String otp;
        public RegisterAPIAsync(String name, String mobile,String device_token,boolean isRegister, String otp) {
            this.name = name;
            this.mobile = mobile;
            this.device_token = device_token;
            this.isRegister = isRegister;
            this.otp = otp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress("Please wait...");
        }

        @Override
        protected String doInBackground(String... strings) {
            API apiInterface = RetrofitClient.getApiClient().create(API.class);
            Call<JsonElement> call;
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("name", name);
            requestData.put("mobile", Long.parseLong(mobile));
            requestData.put("device_token", device_token);
            requestData.put("otp", otp);
            if(isRegister) {
                call = apiInterface.registerShopOwner(requestData);
            } else {
                call = apiInterface.loginShopOwner(requestData);
            }
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                    Log.e("Check_JKData", "RegisterAPIAsync onResponse : "+new Gson().toJson(response.body()));
                    try {
                        if (response.body() != null) {
                            Log.e("Check_Moorthy","res" +response.body().toString());
                            JSONObject root = new JSONObject(response.body().toString());
                            if(root.getString("status").equals(Constants.API_SUCCESS) ){
                                JSONArray dataArray = root.getJSONArray("data");
                                JSONObject data = dataArray.getJSONObject(0);
                                AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.ID,data.getInt("id"));
                                AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.NAME,data.getString("name"));
                                AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.MOBILE,data.getLong("mobile"));
                                Intent i = new Intent(OtpActivity.this, TestMainActivity2.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(OtpActivity.this, ""+root.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        hideProgress();
                    } catch (Exception e) {
                        Log.e("Check_JKData", "RegisterAPIAsync onResponse Catch : "+e.getMessage());
                        Log.e("Check_Moorthy","res" +response.body().toString());
                        hideProgress();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                    Log.e("Check_JKData", "RegisterAPIAsync onFailure : "+t.getMessage());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            hideProgress();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Check_JKNotify", "OTP onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (commonViewModel != null) {
            commonViewModel.getMutableLoginCheck().removeObservers(OtpActivity.this);
        }
        cancelCountdownTimer();
    }

    private void getLoginCheckData() {
        commonViewModel.getMutableLoginCheck().observe(OtpActivity.this, jsonObject -> {
            if (OtpActivity.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if (jsonObject.getBoolean("status")) {

                        } else {
                            Toast.makeText(this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                hideProgress();
            }
        });
    }

    public void getPermission() {
        PermissionX.init(OtpActivity.this)
                .permissions(Manifest.permission.POST_NOTIFICATIONS)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {

                    } else {
                        Utils.openNotifySettingScreen(this);
                    }
                });
    }
}