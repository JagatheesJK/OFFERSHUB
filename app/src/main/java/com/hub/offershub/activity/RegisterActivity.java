package com.hub.offershub.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivityRegisterBinding;
import com.hub.offershub.utils.Utils;
import com.permissionx.guolindev.PermissionX;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding binding;
    private String token = "" , mobile ="" ,name = "";
    private static final int CREDENTIAL_PICKER_REQUEST = 120;
    private SignInClient signInClient;

    // Declare the ActivityResultLauncher
    private final ActivityResultLauncher<IntentSenderRequest> phoneNumberHintLauncher =
            registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                try {
                                    String phoneNumber = signInClient.getPhoneNumberFromIntent(data);
                                    binding.mobileEd.setText("" + Utils.extractTenDigitPhoneNumber(phoneNumber));
                                } catch (ApiException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        signInClient = Identity.getSignInClient(this);
        getPermission();
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
                PermissionX.init(RegisterActivity.this)
                        .permissions(Manifest.permission.POST_NOTIFICATIONS)
                        .request((allGranted, grantedList, deniedList) -> {
                            if (allGranted) {
                                commonViewModel.loginCheck(makeRequest(mobile), myProgressDialog);
                            } else {
                                Utils.openNotifySettingScreen(this);
                            }
                        });
            }
        });

    }

    private void getMobileNumber() {
        GetPhoneNumberHintIntentRequest request = GetPhoneNumberHintIntentRequest.builder().build();
        Task<PendingIntent> hintIntent = signInClient.getPhoneNumberHintIntent(request);
        hintIntent.addOnSuccessListener(pendingIntent -> {
            try {
                // Use the ActivityResultLauncher to launch the intent
                IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(pendingIntent).build();
                phoneNumberHintLauncher.launch(intentSenderRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).addOnFailureListener(e -> e.printStackTrace());


       /* HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent = Credentials.getClient(this).getHintPickerIntent(hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0, new Bundle());
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }*/
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
        }

        if (binding.mobileEd.length() > 0 && binding.mobileEd.length() < 10 ) {
            binding.mobileEd.setError("Enter Valid Mobile Number");
            return false;
        }

        // after all validation return true.
        return true;
    }

    private Map<String, Object> makeRequest(String mobile) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("mobile", Long.parseLong(mobile));
        requestData.put("type", "register");
        requestData.put("device_token", token);
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
                            intent.putExtra("type", "register");
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

    public void getPermission() {
        PermissionX.init(RegisterActivity.this)
                .permissions(Manifest.permission.POST_NOTIFICATIONS)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {

                    } else {
                        Utils.openNotifySettingScreen(this);
                    }
                });
    }
}