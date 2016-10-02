package com.example.sergii.touchcatch;

import android.graphics.Point;
import android.util.Log;
import android.view.WindowManager;

import com.example.sergii.touchcatch.appliers.BasicApplier;
import com.example.sergii.touchcatch.appliers.PositionX;
import com.example.sergii.touchcatch.appliers.PositionY;
import com.example.sergii.touchcatch.appliers.SizeX;
import com.example.sergii.touchcatch.appliers.SizeY;
import com.example.sergii.touchcatch.filemanager.FileLineReader;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii on 02.10.16.
 */
public class ValueHolder {

    private static final String TAG = ValueHolder.class.getSimpleName();
    private static Point DEFAULT_SIZE = new Point( 100, 100 );
    private static Point DEFAULT_POSITION = new Point( 492, 527 );

    private FileLineReader.OnLineReadListener mOnLineReadListener;
    private HashMap< String, BasicApplier> mValueHolder;

    public ValueHolder(){
        initValueHolder();
    }

    public ValueHolder( HashMap< String, BasicApplier > applierHashMap ){
        mValueHolder = applierHashMap;
    }

    private void initValueHolder() {
        mValueHolder = new HashMap<>();

        addValue(new PositionX(DEFAULT_POSITION.x));
        addValue(new PositionY(DEFAULT_POSITION.y));
        addValue(new SizeX(DEFAULT_SIZE.x));
        addValue(new SizeY(DEFAULT_SIZE.y));
    }

    private void addValue(BasicApplier applier) {
        mValueHolder.put(applier.getName(), applier);
    }

    private FileLineReader.OnLineReadListener createFileReadListener() {
        return new FileLineReader.OnLineReadListener() {

            private boolean mResult = false;

            @Override
            public void read(String aString) {
                Log.d(TAG, "read: " + aString);
                Pattern p = Pattern.compile("(\\w*)=\"(\\d*)\"");
                Matcher m = p.matcher(aString);
                if (m.find()) {
                    Log.d(TAG, "read: m.group : " + m.group(1) + " " + m.group(2));
                    mResult |= setValue(m.group(1), Integer.parseInt(m.group(2)));
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

    private boolean setValue(String key, int value) {
        BasicApplier applier = mValueHolder.get(key);
        if ( applier != null && applier.getValue() != value ){
            applier.setValue(value);
            return true;
        }
        return false;
    }

    public List<String> getParamsList() {
        List<String> result = new ArrayList<>(mValueHolder.size());
        for ( String key : mValueHolder.keySet() ){
            BasicApplier value = mValueHolder.get(key);
            if ( value != null ){
                result.add(key);
            }
        }
        return result;
    }

    public FileLineReader.OnLineReadListener getOnLineReadListener() {
        if ( mOnLineReadListener == null ){
            mOnLineReadListener = createFileReadListener();
        }
        return mOnLineReadListener;
    }

    public void applie(WindowManager.LayoutParams layoutParams) {
        for( String key : mValueHolder.keySet()){
            BasicApplier applier = mValueHolder.get(key);
            if ( applier != null ) {
                applier.apply(layoutParams);
            }
        }
    }

    public HashMap<String, BasicApplier> getValueHolder(){
        return mValueHolder;
    }
}
