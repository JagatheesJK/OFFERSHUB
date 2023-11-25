package com.hub.offershub.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseAuth;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.activity.SignActivity;

public class Utils {
    public static void logout(Context context, PrefsHelper prefsHelper) {
        FirebaseAuth.getInstance().signOut();
        prefsHelper.clearAllPref();


        Intent intent = new Intent(context, SignActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
