package com.hub.offershub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hub.offershub.R;
import com.hub.offershub.databinding.ActivityOtpBinding;

public class OtpActivity extends AppCompatActivity {

    private ActivityOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.verifyBtn.setOnClickListener(v -> {
            String num = binding.pinView.getText().toString();
            if (num.isEmpty()) {
                Toast.makeText(OtpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(OtpActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}