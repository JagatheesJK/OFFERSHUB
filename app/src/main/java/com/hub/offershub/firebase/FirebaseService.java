package com.hub.offershub.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.hub.offershub.R;

import java.util.concurrent.atomic.AtomicInteger;

public class FirebaseService extends FirebaseMessagingService {

    private static final String TAG = FirebaseService.class.getSimpleName();
    public static String messageChannelId = "msgChannelId";
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private int getId() {
        return atomicInteger.incrementAndGet();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        Log.e("Check_JKNotify", "onMessageReceived");

        init();

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            String type = remoteMessage.getNotification().getBody();
            NotificationHelper.showNotification(this, title, body, type, messageChannelId, getId());
        } else {
            // Handle foreground notifications if data-only message
            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                String title = remoteMessage.getData().get("title");
                String body = remoteMessage.getData().get("body");
                String type = remoteMessage.getData().get("type");
                NotificationHelper.showNotification(this, title, body, type, messageChannelId, getId());
            }
        }
    }

    private void init() {
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = getString(R.string.message_channel_name);
            String description = getString(R.string.message_channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(messageChannelId, channelName, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}