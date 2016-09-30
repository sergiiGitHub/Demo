package com.example.sergii.touchcatch.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.sergii.touchcatch.FileLineReader;
import com.example.sergii.touchcatch.R;
import com.example.sergii.touchcatch.appliers.BasicApplier;
import com.example.sergii.touchcatch.appliers.PositionX;
import com.example.sergii.touchcatch.appliers.PositionY;
import com.example.sergii.touchcatch.appliers.SizeX;
import com.example.sergii.touchcatch.appliers.SizeY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void setParams(HashMap<String, BasicApplier> mValueHolder) {
        for( String key : mValueHolder.keySet()){
            BasicApplier applier = mValueHolder.get(key);
            if ( applier != null ) {
                applier.apply(mParams);
            }
        }
    }
}
