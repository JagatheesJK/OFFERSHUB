package com.hub.offershub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hub.offershub.R;
import com.hub.offershub.databinding.ActivityLoginBinding;
import com.hub.offershub.utils.WindowUtils;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.hideWindowStatusBar(getWindow());
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignActivity.class));
        });
    }
}