package com.example.sergii.touchcatch.service;

import android.util.Log;
import android.view.WindowManager;

import com.example.sergii.touchcatch.ValueHolder;
import com.example.sergii.touchcatch.appliers.PositionX;
import com.example.sergii.touchcatch.appliers.PositionY;
import com.example.sergii.touchcatch.appliers.SizeX;
import com.example.sergii.touchcatch.appliers.SizeY;
import com.example.sergii.touchcatch.filemanager.FileLineReader;
import com.example.sergii.touchcatch.filemanager.FileManager;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii on 09.10.16.
 */
public class ValueHolderController {

    private static final String TAG = ValueHolderController.class.getSimpleName();

    private ValueHolder mValueHolder;
    private FileManager mFileManager;

    private FileLineReader.OnLineReadListener mOnLineReadListener;

    public ValueHolderController( ){
        mFileManager = new FileManager();
        initValueHolder();
        initLineReadListener();
    }

    private void initValueHolder() {
        mValueHolder = new ValueHolder();
        mValueHolder.addValue( new PositionX(100));
        mValueHolder.addValue( new PositionY(100));
        mValueHolder.addValue(new SizeX(250));
        mValueHolder.addValue(new SizeY(100));
    }

    private void initLineReadListener() {
        mOnLineReadListener = new FileLineReader.OnLineReadListener() {

            private boolean mResult = false;

            @Override
            public void read(String aString) {
                Log.d(TAG, "read: " + aString);
                Pattern p = Pattern.compile("(\\w*)=\"(\\d*)\"");
                Matcher m = p.matcher(aString);
                if (m.find()) {
                    Log.d(TAG, "read: m.group : " + m.group(1) + " " + m.group(2));
                    mResult |= mValueHolder.setValue(m.group(1), Integer.parseInt(m.group(2)));
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

    public boolean scanConfig(File file) {
        Log.d(TAG, "parse: " + file);

        mFileManager.readFile(file, mOnLineReadListener);
        return mOnLineReadListener.getResult();
    }

    public void apply(WindowManager.LayoutParams layoutParams) {
        mValueHolder.applie(layoutParams);
    }
}
