package com.hub.offershub.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.hub.offershub.AppApplication;
import com.hub.offershub.PrefsHelper;
import com.hub.offershub.R;
import com.hub.offershub.activity.SignActivity;
import com.hub.offershub.activity.TestMainActivity2;
import com.hub.offershub.listener.InAppUpdateListener;
import com.hub.offershub.model.PushNotifyModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final int RC_APP_UPDATE = 1001;

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

    public static void testNotify(Context context, PushNotifyModel pushNotifyModel) {
        int notificationId = 10;
        Bitmap bitmap = null;
        try {
            bitmap = Glide
                    .with(context)
                    .asBitmap()
                    .load(R.mipmap.ic_launcher)
                    .submit()
                    .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*Bitmap largeIcon = null;
        try {
            URL url1 = new URL(R.mipmap.ic_launcher);
            largeIcon = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        Intent intent = new Intent(context, TestMainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent itemClickPendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);
        style.setBigContentTitle(pushNotifyModel.title);
        style.setSummaryText(pushNotifyModel.message);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, pushNotifyModel.channelID);
        //Attempt to invoke virtual method 'int android.graphics.Bitmap.getWidth()' on a null object reference  -- BY SAMU(1.4.0)
//        builder.setLargeIcon(createRoundImage(largeIcon) != null ? createRoundImage(largeIcon) : largeIcon)
        builder.setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setTicker(context.getString(R.string.app_name))
                .setWhen(System.currentTimeMillis())
                .setContentTitle(pushNotifyModel.title)
                .setContentText(pushNotifyModel.message)
                .setStyle(style)
                .setAutoCancel(true)
                .setContentIntent(itemClickPendingIntent);

        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //NotificationManager notificationManager = NotificationManagerCompat.from
        Notification notify = builder.build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(notificationId, notify);
    }

    public static void openNotifyInsideSettingScreen(Context context) {
        String id = "msgChannelId";
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, id);
        context.startActivity(intent);
    }

    public static void openNotifySettingScreen(Context context) {
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        context.startActivity(intent);
    }

    public static void downloadAndSaveImage(Context context) {
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.app_logo)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveImageToExternalStorage(context, resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    public static void saveImageToExternalStorage(Context context, Bitmap bitmap) {
        try {
            File imagePath = new File(context.getExternalFilesDir(null), "images");
            imagePath.mkdirs();
            File newFile = new File(imagePath, "app_logo.png");

            FileOutputStream out = new FileOutputStream(newFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();

            shareImage(context, newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void shareImage(Context context, File imageFile) {
        Uri contentUri = FileProvider.getUriForFile(context, "com.hub.offershub.provider", imageFile); // Replace with your package name

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        String shareBody = context.getString(R.string.share_content, "https://play.google.com/store/apps/details?id=" +context.getPackageName());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public static String extractTenDigitPhoneNumber(String input) {
        // Define a regex pattern to match sequences of digits
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        StringBuilder digits = new StringBuilder();

        // Append all digit sequences together
        while (matcher.find()) {
            digits.append(matcher.group());
        }

        // Get the last 10 digits if the total number of digits is more than 10
        if (digits.length() >= 10) {
            return digits.substring(digits.length() - 10);
        } else {
            return digits.toString(); // Return what we have if less than 10 digits
        }
    }

    public static boolean setForceUpdate(Activity activity, String currentversion, InstallStateUpdatedListener listener, AppUpdateManager mAppUpdateManager, InAppUpdateListener inAppUpdateListener) {
        boolean isCheck = true;
        Constants.FORCEUPDATE = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.FORCE_UPDATE, "1.0.0");
        Constants.LITEUPDATE = AppApplication.getInstance().prefsHelper.getPref(PrefsHelper.LITE_UPDATE, "1.0.0");
        if (Constants.FORCEUPDATE != null && Constants.LITEUPDATE != null) {
            String forceUpdate = Constants.FORCEUPDATE;
            String liteUpdate = Constants.LITEUPDATE;
            Log.e("Check_JKUpdateFun", "setForceUpdate forceUpdate : "+forceUpdate);
            Log.e("Check_JKUpdateFun", "setForceUpdate liteUpdate : "+liteUpdate);
            if (!"".equals(forceUpdate) && !"".equals(liteUpdate)) {
                if (checkForUpdate(currentversion, forceUpdate)) {
                    checkInAppUpdate(activity, Constants.IMMEDIATE, listener, mAppUpdateManager, inAppUpdateListener);
                    isCheck = true;
                } else if (checkForUpdate(currentversion, liteUpdate)) {
                    checkInAppUpdate(activity, Constants.FLEXIBLE, listener, mAppUpdateManager, inAppUpdateListener);
                    isCheck = true;
                } else {
                    isCheck = false;
                    //Toast.makeText(activity, "App all already updated!", Toast.LENGTH_SHORT).show();
                }
            } else
                isCheck = false;
        }

        return isCheck;
    }

    public static void checkInAppUpdate(Activity activity, String type, InstallStateUpdatedListener listener, AppUpdateManager mAppUpdateManager, InAppUpdateListener inAppUpdateListener) {
        Constants.isUpdateType = type;
        Log.e("Check_JKUpdateFun", "checkInAppUpdate type : "+type);
        if (type.equalsIgnoreCase("Flexible")) {
            mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new com.google.android.play.core.tasks.OnSuccessListener<AppUpdateInfo>() {
                @Override
                public void onSuccess(AppUpdateInfo appUpdateInfo) {
                    Log.e("Check_JKUpdateFun", "checkInAppUpdate onSuccess : "+new Gson().toJson(appUpdateInfo));
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                            appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                        try {
                            mAppUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE,
                                    activity, RC_APP_UPDATE);
                        } catch (IntentSender.SendIntentException e) {
                            Log.d("Check_JKUpdate", "exception flexible is " + e);
                        }
                    } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                        showCompleteUpdate(activity, mAppUpdateManager);
                    } else {
                        Log.e("Check_JKUpdate", "checkForAppUpdateAvailability: something else");
                    }
                }
            }).addOnFailureListener(new com.google.android.play.core.tasks.OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.d("Check_JKUpdateFun", "update flexible failure is " + e);
                }
            });
            mAppUpdateManager.registerListener(listener);
        } else if (type.equalsIgnoreCase("Immediate")) {
            mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new com.google.android.play.core.tasks.OnSuccessListener<AppUpdateInfo>() {
                @Override
                public void onSuccess(AppUpdateInfo appUpdateInfo) {
                    Log.e("Check_JKUpdateFun", "checkInAppUpdate onSuccess : "+new Gson().toJson(appUpdateInfo));
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE ||
                            appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS &&
                                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                        try {
                            mAppUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE,
                                    activity, RC_APP_UPDATE);

                        } catch (IntentSender.SendIntentException e) {
                            Log.d("Check_JKUpdate", "exception immediate is " + e);
                        }
                    }
                    else if(appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_NOT_AVAILABLE) {
                        inAppUpdateListener.onInAppUpdateStatus(appUpdateInfo);

                    }
                }
            }).addOnFailureListener(new com.google.android.play.core.tasks.OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.e("Check_JKUpdateFun", "update immediate failure is " + e);
                }
            });
        }
    }

    public static boolean checkForUpdate(String existingVersion, String newVersion) {
        Log.e("Check_JKUpdateFun", "checkForUpdate existingVersion : "+existingVersion+" newVersion : "+newVersion);
        if (existingVersion.isEmpty() || newVersion.isEmpty()) {
            return false;
        }

        existingVersion = existingVersion.replaceAll("\\.", "");
        newVersion = newVersion.replaceAll("\\.", "");

        int existingVersionLength = existingVersion.length();
        int newVersionLength = newVersion.length();

        StringBuilder versionBuilder = new StringBuilder();
        if (newVersionLength > existingVersionLength) {
            versionBuilder.append(existingVersion);
            for (int i = existingVersionLength; i < newVersionLength; i++) {
                versionBuilder.append("0");
            }
            existingVersion = versionBuilder.toString();
        } else if (existingVersionLength > newVersionLength) {
            versionBuilder.append(newVersion);
            for (int i = newVersionLength; i < existingVersionLength; i++) {
                versionBuilder.append("0");
            }
            newVersion = versionBuilder.toString();
        }
        return Integer.parseInt(newVersion) > Integer.parseInt(existingVersion);
    }

    public static void showCompleteUpdate(Activity activity, AppUpdateManager manager) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), "New app is ready!",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.completeUpdate();
            }
        });
        snackbar.show();
    }

}
