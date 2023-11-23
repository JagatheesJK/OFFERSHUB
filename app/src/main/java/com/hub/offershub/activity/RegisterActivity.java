package com.hub.offershub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hub.offershub.R;
import com.hub.offershub.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}