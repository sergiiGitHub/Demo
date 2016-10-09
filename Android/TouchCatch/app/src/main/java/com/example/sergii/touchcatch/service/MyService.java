package com.example.sergii.touchcatch.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.sergii.touchcatch.ConfigFile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by s.muzychuk on 9/27/2016.
 */
public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();

    private static boolean isStart = false;
    private ViewHolder mViewHolder = null;
    private ValueHolderController mValueHolderController;

    public static boolean isStart() {
        return isStart;
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        mViewHolder = new ViewHolder(this);
        mValueHolderController = new ValueHolderController();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        //selfDestroy(startId);
        isStart = true;

        updateView();
        mViewHolder.attachView();
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateView() {
        mValueHolderController.scanConfig(ConfigFile.getConfigFile());
        mValueHolderController.apply( mViewHolder.getLayoutParams() );
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        isStart = false;
        mViewHolder.detachView();
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    void selfDestroy(final int startId) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; ++i) {
                    Log.d(TAG, "run: i: " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(startId);
            }
        });
    }
}