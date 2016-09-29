package com.example.sergii.touchcatch.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
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

import java.io.File;
import java.security.PolicySpi;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii on 29.09.16.
 */
public class ViewHolder {

    private static final String TAG = ViewHolder.class.getSimpleName();
    private static final float DEFAULT_VERTICAL_MARGIN = 0.5f;
    private static final float DEFAULT_HORIZONTAL_MARGIN = 0.3f;

    private WindowManager mWindowManager;
    private View mViewOverlay;
    private WindowManager.LayoutParams mParams;
    private Map< String, EValueType> mValueHolder;

    private enum EValueType{
        POSITION_X,
        POSITION_Y,
        SIZE_X,
        SIZE_Y
    }

    public ViewHolder( Context aContext){
        initValueHolder();
        initParams();
        initView(aContext);
        parse();
    }

    private void initValueHolder() {
        // TODO: 29.09.16 use enum
        mValueHolder = new HashMap<>();
        mValueHolder.put("position_x", EValueType.POSITION_X);
        mValueHolder.put("position_y", EValueType.POSITION_Y);
        mValueHolder.put("size_x", EValueType.SIZE_X);
        mValueHolder.put("size_y", EValueType.SIZE_Y);
    }

    private void parse() {
        // Get the directory for the user's public pictures directory.
        new FileLineReader().read(getFile(), new FileLineReader.OnLineRead() {
            @Override
            public void read(String aString) {
                Log.d(TAG, "read: " + aString);
                Pattern p = Pattern.compile("(\\w*)=\"(\\d*)\"");
                Matcher m = p.matcher(aString);
                if (m.find()) {
                    Log.d(TAG, "read: m.group : " + m.group(1) + " " + m.group(2));
                    setValue(m.group(1), Integer.parseInt(m.group(2)));
                }
            }

            @Override
            public int getMaxLine() {
                return 4;
            }
        });
    }

    private void setValue(String key, int value) {
        EValueType event = mValueHolder.get(key);

        if ( event == EValueType.POSITION_X ){
            mParams.x = value;
            Log.d(TAG, "setValue: mParams.horizontalMargin " + mParams.horizontalMargin);
        } else if( event == EValueType.POSITION_Y ){
            mParams.y = value;
            Log.d(TAG, "setValue: mParams.verticalMargin " + mParams.verticalMargin);
        } else if ( event == EValueType.SIZE_X ){
            mParams.width = value;
            Log.d(TAG, "setValue: mParams.x " + mParams.width);
        } else if ( event == EValueType.SIZE_Y ){
            mParams.height = value;
            Log.d(TAG, "setValue: mParams.y " + mParams.height);
        }
    }

    private File getFile() {
        File fileDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "touchCatch");
        if (!fileDir.exists()) {
            Log.e( TAG, "Directory not created");
            return null;
        }

        File file = new File( fileDir, "params.txt" );
        if ( !file.exists() ){
            return null;
        }
        return file;
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

    public void attachView() {
        mWindowManager.addView(mViewOverlay, mParams);
    }

    public void detachView(){
        mWindowManager.removeView(mViewOverlay);
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

        mParams.verticalMargin = DEFAULT_VERTICAL_MARGIN;
        mParams.horizontalMargin = DEFAULT_HORIZONTAL_MARGIN;

        return mParams;
    }
}
