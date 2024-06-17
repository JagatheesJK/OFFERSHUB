package com.hub.offershub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;

import com.google.gson.Gson;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.animation.Animation;
import com.hub.offershub.base.BaseActivity;
import com.hub.offershub.databinding.ActivitySplashBinding;
import com.hub.offershub.utils.WindowUtils;
import com.hub.offershub.viewmodel.CommonViewModel;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;

    CommonViewModel commonViewModel;
    boolean isDelayDone = false;
    boolean isAPIDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.hideWindowStatusBar(getWindow());
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        commonViewModel = new CommonViewModel(AppApplication.getInstance());
        commonViewModel.getCategory(AppApplication.getInstance().prefsHelper, myProgressDialog);
        commonViewModel.getSettingsConfig(myProgressDialog);
        getSettingConfig();

        Animation.startZoomEffect(binding.splashImg);
        enterDelay();
        int userId = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID,0);
        if (userId != 0) {
            getNotifyData();
            commonViewModel.getNotify(makeRequest(), myProgressDialog);
        }
    }

    private void enterDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            isDelayDone = true;
            int userId = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID,0);
            if(userId !=0) {
                if (isAPIDone) {
                    Intent i = new Intent(SplashActivity.this, TestMainActivity2.class);
                    i.putExtra("notifyCount", notifyCount);
                    startActivity(i);
                    finish();
                }
            } else {
                Intent i = new Intent(SplashActivity.this, SignActivity.class);
                startActivity(i);
                finish();
            }
        }, 3500);
    }

    private Map<String, Object> makeRequest() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("shopownerid", AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.ID));
        return requestData;
    }

    private int notifyCount = 0;
    private void getNotifyData() {
        commonViewModel.getMutableNotifyData().observe(SplashActivity.this, bookModel -> {
            if (SplashActivity.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (bookModel != null) {
                    Log.e("Check_JKNotifyData", "Splash getNotifyData : "+new Gson().toJson(bookModel));
                    isAPIDone = true;
                    if(bookModel.status.equals("success")) {
                        notifyCount = bookModel.count;
                        AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.NOTIFY_COUNT, notifyCount);
                    } else {
                        notifyCount = 0;
                    }
                    if (isDelayDone) {
                        Intent i = new Intent(SplashActivity.this, TestMainActivity2.class);
                        i.putExtra("notifyCount", notifyCount);
                        startActivity(i);
                        finish();
                    }
                } else {
                    if (isDelayDone) {
                        Intent i = new Intent(SplashActivity.this, TestMainActivity2.class);
                        i.putExtra("notifyCount", notifyCount);
                        startActivity(i);
                        finish();
                    }
                    // For Testing
                    Toast.makeText(this, "Notify API Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (commonViewModel != null) {
            commonViewModel.getMutableNotifyData().removeObservers(SplashActivity.this);
            commonViewModel.getMutableSettingData().removeObservers(SplashActivity.this);
        }
    }

    private void getSettingConfig() {
        commonViewModel.getMutableSettingData().observe(SplashActivity.this, settingModel -> {
            if (SplashActivity.this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
                if (settingModel != null) {
                    try {
                        AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.FORCE_UPDATE, settingModel.force_update);
                        AppApplication.getInstance().prefsHelper.savePref(PrefsHelper.LITE_UPDATE, settingModel.lite_update);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}