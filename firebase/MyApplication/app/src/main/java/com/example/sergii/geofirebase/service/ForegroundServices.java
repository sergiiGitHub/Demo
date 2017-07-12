package com.example.sergii.geofirebase.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sergii.geofirebase.MainActivity;

/**
 * Created by sergii on 11.07.17.
 */

public class ForegroundServices extends Service {
    private static final String TAG = ForegroundServices.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        if (intent.getAction().equals(ServiceController.ACTION.START_FOREGROUND_ACTION)) {
            Log.i(TAG, "Received Start Foreground Intent ");
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(ServiceController.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Truiton Music Player")
                    .setTicker("Truiton Music Player")
                    .setContentText("My Music")
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .build();
//                    .addAction(android.R.drawable.ic_media_previous,
//                            "Previous", ppreviousIntent)
//                    .addAction(android.R.drawable.ic_media_play, "Play",
//                            pplayIntent)
//                    .addAction(android.R.drawable.ic_media_next, "Next",
//                            pnextIntent).build();
            startForeground(ServiceController.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);
//        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
//            Log.i(LOG_TAG, "Clicked Previous");
//        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
//            Log.i(LOG_TAG, "Clicked Play");
//        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
//            Log.i(LOG_TAG, "Clicked Next");
        } else if (intent.getAction().equals(
                ServiceController.ACTION.STOP_FOREGROUND_ACTION)) {
            Log.i(TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }
}
