package com.hub.offershub.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.hub.offershub.R;
import com.hub.offershub.activity.SplashActivity;


public class NotificationHelper {

    public static void showNotification(Context context, String title, String message, String type, String channelID, int ID) {
        // Create an intent that will be fired when the user taps the notification
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        // Use a custom layout for the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelID)
                .setTicker(context.getString(R.string.app_name))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.circle_logo))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));
//                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());
//                .setCustomContentView(getCustomDesign(title, message));
        if ("main".equals(type))
            notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID, notificationBuilder.build());
    }
}
