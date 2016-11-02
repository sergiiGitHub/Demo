package com.example.sergii.touchcatch.appliers;

import android.view.WindowManager;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by sergii on 30.09.16.
 */
public abstract class BasicApplier implements IValueApplier, Serializable {

    private String mName;
    private int mValue;

    public BasicApplier( String name, int value ){
        this.mName = name;
        this.mValue = value;
    }

    @Override
    public abstract void apply(WindowManager.LayoutParams aParams);

    public String getName() {
        return mName;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value){
        mValue = value;
    }

    private void writeObject(ObjectOutputStream os) {
        // throws IOException {
        try {
            os.defaultWriteObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject( ObjectInputStream is ){
        try {
            is.defaultReadObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return mName + "=" + mValue;
    }
}
