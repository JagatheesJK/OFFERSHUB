package com.hub.offershub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hub.offershub.R;
import com.hub.offershub.databinding.ActivitySignBinding;
import com.hub.offershub.utils.WindowUtils;

public class SignActivity extends AppCompatActivity {

    private ActivitySignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.hideWindowStatusBar(getWindow());
        binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nxtBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignActivity.this, MainActivity.class));
        });

        binding.registerTxt.setOnClickListener(v -> {
            startActivity(new Intent(SignActivity.this, RegisterActivity.class));
        });
    }
}