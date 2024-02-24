package com.hub.offershub.activity;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityRegisterBinding;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding binding;
    private String token = "" , mobile ="" ,name = "";
    private static final int CREDENTIAL_PICKER_REQUEST = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d("TAG", "getInstanceId failed", task.getException());
                return;
            }
            token = task.getResult();
            Log.e("token1", token);
            getMobileNumber();
        });

        getLoginCheckData();

        binding.nxtBtn.setOnClickListener(v -> {
            if (CheckAllFields()) {
                mobile = binding.mobileEd.getText().toString().trim();
                name = binding.regNameEd.getText().toString();
                commonViewModel.loginCheck(makeRequest(mobile), myProgressDialog);
            }
        });

    }

    private void getMobileNumber() {
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent = Credentials.getClient(this).getHintPickerIntent(hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0, new Bundle());
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREDENTIAL_PICKER_REQUEST & resultCode == RESULT_OK) {
            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);

            binding.mobileEd.setText("" + credential.getId().substring(3));
        } else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE) {
            Toast.makeText(this, "No phone numbers found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean CheckAllFields() {
        if (binding.regNameEd.length() == 0) {
            binding.regNameEd.setError("Input required");
            binding.regNameEd.requestFocus();
            return false;
        } else {
            binding.regNameEd.setError(null);
        }

        if (binding.mobileEd.length() == 0) {
            binding.mobileEd.setError("Input required");
            binding.mobileEd.requestFocus();
            return false;
        }  else if(binding.mobileEd.length() > 0 && binding.mobileEd.length() < 10 ) {
            binding.mobileEd.setError("Enter Valid Mobile Number");
        }

        // after all validation return true.
        return true;
    }

    private Map<String, Object> makeRequest(String mobile) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("mobile", Long.parseLong(mobile));
        return requestData;
    }

    private void getLoginCheckData() {
        commonViewModel.getMutableLoginCheck().observe(RegisterActivity.this, jsonObject -> {
            if (RegisterActivity.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(!jsonObject.getBoolean("status")) {
                            Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                            intent.putExtra("mobile", mobile);
                            intent.putExtra("name", name);
                            intent.putExtra("token", token);
                            intent.putExtra("isRegister", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (commonViewModel != null)
            commonViewModel.getMutableLoginCheck().removeObservers(RegisterActivity.this);
    }
}