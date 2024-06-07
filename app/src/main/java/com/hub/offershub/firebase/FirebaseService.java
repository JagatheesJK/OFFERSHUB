package com.hub.offershub.firebase;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.hub.offershub.R;
import com.hub.offershub.activity.TestMainActivity2;
import com.hub.offershub.model.PushNotifyModel;
import com.hub.offershub.utils.Constants;

import org.greenrobot.eventbus.EventBus;

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

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            Log.e("Check_JKNotify", "onMessageReceived title : "+title);
            Log.e("Check_JKNotify", "onMessageReceived body : "+body);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Log.e("Check_JKNotify", "onMessageReceived Notify title : "+title);
            Log.e("Check_JKNotify", "onMessageReceived Notify body : "+body);
            Log.e("Check_JKNotify", "onMessageReceived Body : "+remoteMessage.getNotification().getBody());
            defaultNotification(title, body, messageChannelId);
        } else {
            // Handle foreground notifications if data-only message
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            defaultNotification(title, body, messageChannelId);
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

    private RemoteViews getCustomDesign(String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(),
                R.layout.notify_layout);
        remoteViews.setTextViewText(R.id.notifyTitle, title);
        remoteViews.setTextViewText(R.id.notifyBody, message);
        remoteViews.setImageViewResource(R.id.notifyIcon,
                R.mipmap.ic_launcher_foreground);
        return remoteViews;
    }

    private void defaultNotification(String title, String message, String channelID) {
        PushNotifyModel pushNotifyModel = new PushNotifyModel(title, message, channelID);
        Log.e("Check_JKNotify","defaultNotification Back : "+ Constants.ISAPPFORGROUNT);
        if (Constants.ISAPPFORGROUNT) {
            int notificationId = getId();
            Log.e("Check_JKNotify", "defaultNotification notificationId : "+notificationId);
            RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notify_layout);
            remoteViews.setTextViewText(R.id.notifyTitle, title);
            remoteViews.setTextViewText(R.id.notifyBody, message);

            Intent intent = new Intent(this, TestMainActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelID)
                    .setTicker(this.getString(R.string.app_name))
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setCustomContentView(remoteViews)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = notification.build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(notificationId, notify);
        } else {
            Log.e("Check_JKNotify","defaultNotification pushNotifyModel : "+new Gson().toJson(pushNotifyModel));
            if(pushNotifyModel != null)
                EventBus.getDefault().post(pushNotifyModel);
        }
    }
}