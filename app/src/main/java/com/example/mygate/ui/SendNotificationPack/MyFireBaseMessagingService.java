package com.example.mygate.ui.SendNotificationPack;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.mygate.R;
import com.example.mygate.ui.NotificationValidator;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    private static final String YOUR_DESIRED_CHANNEL_ID_STRING = "channel_02" ;
    private static final CharSequence YOUR_DESIRED_CHANNEL_LABEL_STRING ="channel" ;
    private static final String YOUR_DESIRED_CHANNEL_DESC_STRING = "channel_03";
    String title,message;
    String CHANNEL_ID = "channel_01";
    PendingIntent ci;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        title=remoteMessage.getData().get("Title");
        message=remoteMessage.getData().get("Message");
        Intent nid = new Intent(getApplicationContext(), NotificationValidator.class);
        // If you were starting a service, you wouldn't using getActivity() here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ci = PendingIntent.getActivity(getApplicationContext(),
                    0, nid, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        }else {
            ci = PendingIntent.getActivity(getApplicationContext(),
                    0, nid, PendingIntent.FLAG_UPDATE_CURRENT);
        }
//        PendingIntent ci = PendingIntent.getActivity(getApplicationContext(), 0, nid, 0);

//        Notification notification = new Notification.Builder(MainActivity.this)
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel chan1 = new NotificationChannel(
                    YOUR_DESIRED_CHANNEL_ID_STRING,
                    YOUR_DESIRED_CHANNEL_LABEL_STRING,
                    NotificationManager.IMPORTANCE_DEFAULT);

            chan1.setDescription(YOUR_DESIRED_CHANNEL_DESC_STRING);//OPTIONAL
            chan1.setLightColor(Color.BLUE);//OPTIONAL
            chan1.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);//OPTIONAL
            chan1.setShowBadge(true);//OPTIONAL
            NotificationCompat.Builder builder=
                    new NotificationCompat.Builder(getApplicationContext(), YOUR_DESIRED_CHANNEL_ID_STRING)
                            .setSmallIcon(R.drawable.notif_logo)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setChannelId(CHANNEL_ID)
                            .setSound(defaultSoundUri)
                            .setVibrate(new long[] { 1000, 1000 })
                            .setContentIntent(ci)
                            .setAutoCancel(true);
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(chan1);
            manager.notify(0, builder.build());
        }else{
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                            .setSmallIcon(R.drawable.notif_logo)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setChannelId(CHANNEL_ID)
                            .setSound(defaultSoundUri)
                            .setVibrate(new long[] { 1000, 1000 })
                            .setContentIntent(ci)
                            .setAutoCancel(true);
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }

}

