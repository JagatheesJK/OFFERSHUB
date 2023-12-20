package com.hub.offershub.activity;

import androidx.annotation.NonNull;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.JsonElement;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityOtpBinding;
import com.hub.offershub.retrofit.API;
import com.hub.offershub.retrofit.RetrofitClient;
import com.hub.offershub.base.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends BaseActivity {
    String name="",mobile="", token = "";
    boolean isRegister = false;

    Context context;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private FirebaseAuth mAuth;
    private ActivityOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        context = this;
        mobile=getIntent().getStringExtra("mobile");
        token = getIntent().getStringExtra("token");
        name = getIntent().getStringExtra("name");
        isRegister = getIntent().getBooleanExtra("isRegister",false);
        callBack();
        sendOtp("+91" + mobile);
        binding.verifyBtn.setOnClickListener(v -> {
            String code = binding.pinView.getText().toString();
            if (code.length() < 6) {
                binding.pinView.setError("Enter Valid code");
            } else {
                //Cannot create PhoneAuthCredential without either verificationProof, sessionInfo, temporary proof, or enrollment ID. --- BY SAMU(1.2.5)
                try {
                    if(mVerificationId != null){
                        Log.d("TAG","otp next btn clicked");
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
                        signInWithPhoneAuthCredential(credential);
                    }else{
                        Toast.makeText(OtpActivity.this, "Please verify internet", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendOtp(String mobile) {
        showProgress(getString(R.string.please_wait));
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                OtpActivity.this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private void callBack() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                binding.pinView.setText(credential.getSmsCode());
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(OtpActivity.this, "Invalid Request", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(OtpActivity.this, "The SMS quota for the project has been exceeded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OtpActivity.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
                hideProgress();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                hideProgress();
                Toast.makeText(OtpActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(OtpActivity.this, task -> {
            if (task.isSuccessful()) {
                RegisterAPIAsync registerAPIAsync = new RegisterAPIAsync(name,mobile,token,isRegister);
                registerAPIAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(OtpActivity.this, "Invalid code", Toast.LENGTH_SHORT).show();
                }
            }
            hideProgress();
        });
    }


    public class RegisterAPIAsync extends AsyncTask<String, String, String> {
        Activity activity;
        int check;
        String name; String mobile;String device_token; boolean isRegister;
        public RegisterAPIAsync(String name, String mobile,String device_token,boolean isRegister) {
            this.name = name;
            this.mobile = mobile;
            this.device_token = device_token;
            this.isRegister = isRegister;
        }

        @Override
        protected String doInBackground(String... strings) {
            API apiInterface = RetrofitClient.getApiClient().create(API.class);
            Call<JsonElement> call;
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("name",name);
            requestData.put("mobile",Long.parseLong(mobile));
            requestData.put("device_token",device_token);
            if(isRegister) {
                call = apiInterface.registerShopOwner(requestData);
            } else {
                call = apiInterface.loginShopOwner(requestData);
            }
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
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
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(OtpActivity.this, ""+root.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Check_Moorthy","res" +response.body().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {

                }
            });
            return null;
        }
    }
}