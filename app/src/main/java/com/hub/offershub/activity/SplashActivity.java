package com.hub.offershub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.animation.Animation;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivitySplashBinding;
import com.hub.offershub.utils.WindowUtils;
import com.hub.offershub.viewmodel.CommonViewModel;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;

    CommonViewModel commonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.hideWindowStatusBar(getWindow());
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        commonViewModel = new CommonViewModel(AppApplication.getInstance());
        commonViewModel.getCategory(AppApplication.getInstance().prefsHelper, myProgressDialog);

        Animation.startZoomEffect(binding.splashImg);
        enterDelay();
    }

    private void enterDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            int userId = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID,0);
            if(userId !=0) {
                Intent i = new Intent(SplashActivity.this, TestMainActivity2.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(SplashActivity.this, SignActivity.class);
                startActivity(i);
                finish();
            }
        }, 3500);
    }
}