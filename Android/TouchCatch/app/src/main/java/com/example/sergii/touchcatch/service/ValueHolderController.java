package com.example.sergii.touchcatch.service;

import android.util.Log;
import android.view.WindowManager;

import com.example.sergii.touchcatch.appliers.BasicApplier;
import com.example.sergii.touchcatch.appliers.PositionX;
import com.example.sergii.touchcatch.appliers.PositionY;
import com.example.sergii.touchcatch.appliers.SizeX;
import com.example.sergii.touchcatch.appliers.SizeY;
import com.example.sergii.touchcatch.filemanager.FileLineReader;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii on 09.10.16.
 */
public class ValueHolderController {

    private static final String TAG = ValueHolderController.class.getSimpleName();

    private FileLineReader.OnLineReadListener mOnLineReadListener;
    private static ValueHolderController mInstance;
    private HashMap< String, BasicApplier> mValueHolder;
    private Pattern mPattern = Pattern.compile("(\\w*)=\"(\\d*)\"");

    public static ValueHolderController getInstance(){
        if ( mInstance == null ){
            mInstance = new ValueHolderController();
        }
        return mInstance;
    }

    private ValueHolderController( ){
        initValueHolder();
        initLineReadListener();
    }

    private void initValueHolder() {
        mValueHolder = new HashMap<>();
        addValue(new PositionX(100));
        addValue(new PositionY(100));
        addValue(new SizeX(250));
        addValue(new SizeY(100));
    }

    public void addValue(BasicApplier applier) {
        mValueHolder.put(applier.getName(), applier);
    }

    private void initLineReadListener() {
        mOnLineReadListener = new FileLineReader.OnLineReadListener() {

            private boolean mResult;

            @Override
            public void read(String aString) {
                mResult = true;
                Log.d(TAG, "read: " + aString);
                Matcher m = mPattern.matcher(aString);
                if (m.find()) {
                    Log.d(TAG, "read: m.group : " + m.group(1) + " " + m.group(2));
                    mResult |= setValue(m.group(1), Integer.parseInt(m.group(2)));
                } else {
                    Log.e(TAG, "read: could parse: " + aString );
                }
            }

            @Override
            public int getMaxLine() {
                return mValueHolder.size();
            }

            @Override
            public boolean getResult() {
                return mResult;
            }
        };
    }

    public boolean setValue(String key, int value) {
        BasicApplier applier = mValueHolder.get(key);
        if ( applier != null && applier.getValue() != value ){
            applier.setValue(value);
            return true;
        }
        return false;
    }

    public boolean scanConfig(File file) {
        Log.d(TAG, "scanConfig: " + file);

        new FileLineReader().read(file, mOnLineReadListener);
        return mOnLineReadListener.getResult();
    }

    public void apply(WindowManager.LayoutParams layoutParams) {
        for( String key : mValueHolder.keySet()){
            BasicApplier applier = mValueHolder.get(key);
            if ( applier != null ) {
                applier.apply(layoutParams);
            }
        }
    }

    public String generateConfig() {
        StringBuilder stringBuilder = new StringBuilder();
        for( String key : mValueHolder.keySet()){
            BasicApplier applier = mValueHolder.get(key);
            if ( applier != null ) {
                stringBuilder.append(getWriteString(applier));
            }
        }
        return stringBuilder.toString();
    }

    public String getWriteString(BasicApplier applier){
        StringBuilder builder = new StringBuilder();
        builder.append(applier.getName())
                .append("=")
                .append("\"")
                .append(applier.getValue())
                .append("\"")
                .append("\n");
        return builder.toString();
    }
}
