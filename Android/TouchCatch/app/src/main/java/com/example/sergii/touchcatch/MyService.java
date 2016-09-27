package com.example.sergii.touchcatch;


import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by s.muzychuk on 9/27/2016.
 */
public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();

    private ExecutorService es;

    private static boolean isStart = false;
    private WindowManager mWindowManager;
    private ImageView mImageView;

    public static boolean isStart() {
        return isStart;
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        es = Executors.newFixedThreadPool(1);
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        initView();
    }

    private void initView() {
        mImageView = new ImageView(this){
            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                return true;
            }
        };
        mImageView.setBackgroundColor(Color.argb(100, 50, 0, 0));
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        someTask(startId);
        isStart = true;
        addView();

        return super.onStartCommand(intent, flags, startId);
    }

    private void addView() {
        mWindowManager.addView(mImageView, getParameters());
    }

    private ViewGroup.LayoutParams getParameters() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT | Gravity.BOTTOM;
        params.setTitle("Any Title");
        return params;
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        isStart = false;
        mWindowManager.removeView(mImageView);
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    void someTask(final int startId) {
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