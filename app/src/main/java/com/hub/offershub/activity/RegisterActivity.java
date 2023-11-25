package com.hub.offershub.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.hub.offershub.R;
import com.hub.offershub.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

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

        binding.nxtBtn.setOnClickListener(v -> {
            mobile = binding.mobileEd.getText().toString().trim();
            name = binding.regNameEd.getText().toString();
            if(!"".equals(mobile)) {
                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                intent.putExtra("mobile", mobile);
                intent.putExtra("name", name);
                intent.putExtra("token", token);
                intent.putExtra("isRegister", true);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Enter the Mobile Number", Toast.LENGTH_SHORT).show();
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
}