package com.hub.offershub.utils;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.activity.SignActivity;
import com.hub.offershub.activity.TestMainActivity2;
import com.hub.offershub.model.PushNotifyModel;

import java.text.NumberFormat;
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

    public static String addComma(double value) {
        // Create a NumberFormat instance with commas
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);
        // Format the amount with commas
        String formattedAmount = numberFormat.format(value);
        return formattedAmount;
    }

    public static int removeComma(String value) {
        // Create a NumberFormat instance with commas
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);
        try {
            // Parse the formatted amount string to a numeric value
            Number number = numberFormat.parse(value);
            // Retrieve the numeric value without commas
            int amountWithoutCommas = number.intValue();
            return amountWithoutCommas;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void showNotification(Context context, PushNotifyModel pushNotifyModel) {
        Log.e("Check_JKNotify","showNotification");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    pushNotifyModel.channelID,
                    "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // Create an intent that will be fired when the user taps the notification
        Intent intent = new Intent(context, TestMainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        // Use a custom layout for the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, pushNotifyModel.channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle(pushNotifyModel.title)
                .setContentText(pushNotifyModel.message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());
//                .setCustomContentView(createCustomContentView(title, message));

        notificationManager.notify(0, notificationBuilder.build());
        Log.e("Check_JKNotify","showNotification notificationBuilder : "+notificationBuilder.getNotification());
    }
}
