package com.example.sergii.touchcatch.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.sergii.touchcatch.R;
import com.example.sergii.touchcatch.ValueHolder;
import com.example.sergii.touchcatch.appliers.BasicApplier;
import java.util.HashMap;

/**
 * Created by sergii on 29.09.16.
 */
public class ViewHolder {

    private static final String TAG = ViewHolder.class.getSimpleName();

    private WindowManager mWindowManager;
    private View mViewOverlay;
    private WindowManager.LayoutParams mParams;

    public ViewHolder( Context aContext ){
        initView(aContext);
        initParams();
    }

    private void initView(Context aContext) {
        mWindowManager = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mViewOverlay = inflater.inflate(R.layout.overlay_layout, null);
        mViewOverlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: res: " + event.getAction());
                return true;
            }
        });
    }

    private ViewGroup.LayoutParams initParams() {
        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        mParams.gravity = Gravity.TOP | Gravity.LEFT;


//        mParams.width = DEFAULT_SIZE.x;
//        mParams.height = DEFAULT_SIZE.y;
//
//        mParams.x = DEFAULT_POSITION.x;
//        mParams.y = DEFAULT_POSITION.y;

        return mParams;
    }

    public void attachView() {
        mWindowManager.addView(mViewOverlay, mParams);
    }

    public void detachView(){
        mWindowManager.removeView(mViewOverlay);
    }

    public WindowManager.LayoutParams getLayoutParams( ){
        return mParams;
    }

}
