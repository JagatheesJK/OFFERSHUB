package com.hub.offershub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.animation.Animation;
import com.hub.offershub.databinding.ActivitySplashBinding;
import com.hub.offershub.utils.WindowUtils;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.hideWindowStatusBar(getWindow());
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation.startZoomEffect(binding.splashImg);
        enterDelay();
    }

    private void enterDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            String userId = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID,"");
            /*if(userId !="") {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(SplashActivity.this, SignActivity.class);
                startActivity(i);
                finish();
            }*/

            Intent i = new Intent(SplashActivity.this, TestMainActivity2.class);
            startActivity(i);
            finish();
        }, 3500);
    }
}