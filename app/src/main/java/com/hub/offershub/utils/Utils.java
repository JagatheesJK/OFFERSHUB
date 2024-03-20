package com.hub.offershub.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.activity.SignActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static void logout(Context context, PrefsHelper prefsHelper) {
        FirebaseAuth.getInstance().signOut();
        prefsHelper.clearAllPref();


        Intent intent = new Intent(context, SignActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static int dpToPx(Activity activity, float dp) {
        return (int) (dp * activity.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static String convertDateFormat(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        try {
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String milliToDate(long milliSeconds) {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return format.format(calendar.getTime());
    }
}
