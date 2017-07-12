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
import com.example.sergii.geofirebase.R;
import com.example.sergii.geofirebase.location.RealLocationController;

/**
 * Created by sergii on 11.07.17.
 */

public class ForegroundServices extends Service {
    private static final String TAG = ForegroundServices.class.getSimpleName();
    private static boolean isStarted = false;
    private RealLocationController realLocationController;

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
            toggleState();
            Log.i(TAG, "Received Start Foreground Intent ");
            PendingIntent pendingIntent = createPendingIntent();

            Notification notification = createNotification(pendingIntent);
            startForeground(ServiceController.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);

            beginTracking();
        } else if (intent.getAction().equals(ServiceController.ACTION.STOP_FOREGROUND_ACTION)) {
            Log.i(TAG, "Received Stop Foreground Intent");
            stopTRacking();
            toggleState();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    private void stopTRacking() {
        if ( realLocationController != null){
            realLocationController.stopWriteGeoLocation();
            realLocationController = null;
        }
    }

    private void beginTracking() {
        if (realLocationController == null) {
            realLocationController = new RealLocationController(getApplicationContext());
        }
        realLocationController.startWriteGeoLocation();
    }

    private PendingIntent createPendingIntent() {
        final Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(ServiceController.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return PendingIntent.getActivity(this, 0, notificationIntent, 0);
    }

    private Notification createNotification(PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(this)
                .setContentTitle(getApplication().getString(R.string.app_name))
                .setTicker(getApplication().getString(R.string.app_name) + 1)
                .setContentText(getApplication().getString(R.string.services_writing_geo_location))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();
    }

    private void toggleState() {
        isStarted = !isStarted;
    }

    public static boolean isStarted(){
        return isStarted;
    }
}
