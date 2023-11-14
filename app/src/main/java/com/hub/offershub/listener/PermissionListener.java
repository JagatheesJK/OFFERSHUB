package com.hub.offershub.listener;

public interface PermissionListener {
    void onPermissionGranted();
    void onPermissionDenied();
}
