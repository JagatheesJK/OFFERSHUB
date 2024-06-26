package com.hub.offershub.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
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
import com.hub.offershub.databinding.ActivitySignBinding;
import com.hub.offershub.utils.Utils;
import com.hub.offershub.utils.WindowUtils;
import com.permissionx.guolindev.PermissionX;

import java.util.HashMap;
import java.util.Map;

public class SignActivity extends BaseActivity {

    private ActivitySignBinding binding;
    private String token = "" , mobile ="";
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
        WindowUtils.hideWindowStatusBar(getWindow());
        binding = ActivitySignBinding.inflate(getLayoutInflater());
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
                PermissionX.init(SignActivity.this)
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

        binding.registerTxt.setOnClickListener(v -> {
            startActivity(new Intent(SignActivity.this, RegisterActivity.class));
        });

        binding.termsTxt.setOnClickListener(v -> {
            openUrlInDefaultBrowser("http://offershubindia.in/businessterms.html");
        });

        binding.privacyTxt.setOnClickListener(v -> {
            openUrlInDefaultBrowser("http://offershubindia.in/businessprivacy.html");
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
        requestData.put("type", "login");
        requestData.put("device_token", token);
        return requestData;
    }

    private void getLoginCheckData() {
        commonViewModel.getMutableLoginCheck().observe(SignActivity.this, jsonObject -> {
            if (SignActivity.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (jsonObject != null) {
                    try {
                        if(jsonObject.getBoolean("status")) {
                            Intent intent = new Intent(SignActivity.this, OtpActivity.class);
                            intent.putExtra("mobile", mobile);
                            intent.putExtra("token", token);
                            intent.putExtra("type", "login");
                            startActivity(intent);
                        } else {
                            Intent i = new Intent(SignActivity.this, RegisterActivity.class);
                            i.putExtra("mobile", mobile);
                            startActivity(i);
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
            commonViewModel.getMutableLoginCheck().removeObservers(SignActivity.this);
    }

    public void openUrlInDefaultBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(SignActivity.this,"Not supported",Toast.LENGTH_SHORT).show();
        }
    }

    public void getPermission() {
        PermissionX.init(SignActivity.this)
                .permissions(Manifest.permission.POST_NOTIFICATIONS)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {

                    } else {
                        Utils.openNotifySettingScreen(this);
                    }
                });
    }
}