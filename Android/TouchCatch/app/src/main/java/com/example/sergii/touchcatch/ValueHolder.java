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

    private HashMap< String, BasicApplier> mValueHolder;

    public ValueHolder(){
        initValueHolder();
    }

    private void initValueHolder() {
        mValueHolder = new HashMap<>();
    }

    public void addValue(BasicApplier applier) {
        mValueHolder.put(applier.getName(), applier);
    }

    public boolean setValue(String key, int value) {
        BasicApplier applier = mValueHolder.get(key);
        if ( applier != null && applier.getValue() != value ){
            applier.setValue(value);
            return true;
        }
        return false;
    }

//    public List<String> getParamsList() {
//        List<String> result = new ArrayList<>(mValueHolder.size());
//        for ( String key : mValueHolder.keySet() ){
//            BasicApplier value = mValueHolder.get(key);
//            if ( value != null ){
//                result.add(key);
//            }
//        }
//        return result;
//    }

    public void applie(WindowManager.LayoutParams layoutParams) {
        for( String key : mValueHolder.keySet()){
            BasicApplier applier = mValueHolder.get(key);
            if ( applier != null ) {
                applier.apply(layoutParams);
            }
        }
    }

    public int size() {
        return mValueHolder.size();
    }
}
