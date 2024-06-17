package com.hub.offershub.listener;

import com.google.android.play.core.appupdate.AppUpdateInfo;

public interface InAppUpdateListener {
    void onInAppUpdateStatus(AppUpdateInfo appUpdateInfo);
}