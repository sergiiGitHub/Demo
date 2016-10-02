package com.example.sergii.touchcatch.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.sergii.touchcatch.MainActivity;
import com.example.sergii.touchcatch.ValueHolder;
import com.example.sergii.touchcatch.appliers.BasicApplier;

import java.util.HashMap;
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
    private ValueHolder mValueHolder;

    public static boolean isStart() {
        return isStart;
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        //selfDestroy(startId);
        isStart = true;
        if ( mViewHolder == null ) {
            mViewHolder = new ViewHolder(this);
        }
        if ( intent != null ){
            updateView(intent);
        }
        mViewHolder.attachView();
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateView(Intent intent) {
        if ( intent.hasExtra(MainActivity.EXTRA_DATA) ){
            mValueHolder = new ValueHolder((HashMap<String, BasicApplier>) intent.getSerializableExtra(MainActivity.EXTRA_DATA));
            mValueHolder.applie(mViewHolder.getLayoutParams());
            Log.d(TAG, "onStartCommand: mValueHolder " + mValueHolder);
        }
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