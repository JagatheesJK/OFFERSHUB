package com.hub.offershub;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hub.offershub.utils.Constants;

public class AppApplication extends Application implements Application.ActivityLifecycleCallbacks {

    public PrefsHelper prefsHelper;
    static AppApplication app;

    private int activityReferences = 0;
    private boolean isActivityChangingConfigurations = false;

    public static AppApplication getInstance() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        init();
        registerActivityLifecycleCallbacks(this);
    }

    private void init() {
        prefsHelper = new PrefsHelper(AppApplication.this);

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            // App enters foreground
            Log.e("backroundforeground", "forground");
            Constants.ISAPPFORGROUNT = true;
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations();
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            // App enters background
            Log.e("backround", "backround");
            Constants.ISAPPFORGROUNT = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
