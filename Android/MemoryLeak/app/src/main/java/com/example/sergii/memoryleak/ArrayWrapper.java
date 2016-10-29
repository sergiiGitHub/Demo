package com.example.sergii.memoryleak;

import android.os.Handler;
import android.util.Log;

/**
 * Created by sergii on 29.10.16.
 */
public class ArrayWrapper {

    private static final String TAG = ArrayWrapper.class.getSimpleName();

    private final int id;
    private final int[] datas;
    private Handler mHandler;
    private Runnable mRunnable;

    public ArrayWrapper(int id) {
        this.id = id;
        datas = new int[100000];
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");
            }
        };
        /*
        Potential memory leak
        mHandler - if doesn't call remove call back when GC it will not free memory.
        and parent remove from root we don't have reference when another GC come
         */
        mHandler.postDelayed(mRunnable, 1000 * 10);
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "finalize: id: " + id);
        super.finalize();
    }

    public void destroy() {
        mHandler.removeCallbacks(mRunnable);
    }
}
