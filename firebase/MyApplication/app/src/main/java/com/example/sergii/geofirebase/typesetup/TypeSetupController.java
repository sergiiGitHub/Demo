package com.example.sergii.geofirebase.typesetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.example.sergii.geofirebase.IStepHandler;
import com.example.sergii.geofirebase.R;

/**
 * Created by sergii on 09.07.17.
 */

public class TypeSetupController implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = TypeSetupController.class.getSimpleName();
    private static final String TYPE = "SharedAppTypeControllerType";
    private final SharedPreferences sharedPreference;
    private final FragmentManager fragmentManager;
    private TypeSetupFragment fragment;

    private Type currentType;
    private IStepHandler stepHandler;

    public TypeSetupController(FragmentActivity activity) {

        fragmentManager = activity.getSupportFragmentManager();
        sharedPreference = activity.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        currentType = Type.values()[sharedPreference.getInt(TYPE, Type.NON.ordinal())];
        iniFragment();
    }

    private void iniFragment() {
        fragment = new TypeSetupFragment();
        fragment.setOnItemSelectedListener(this);
        fragment.setOnItemClickListener(this);
        fragment.setType(currentType);
    }

    private void setType(Type type) {
        Log.d(TAG, "setType: type: " + type + "; currentType: " + currentType);
        if(currentType == type){
            return;
        }
        currentType = type;
        updateSharedPreference(TYPE, currentType.ordinal());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type_setup_done:
                updateSharedPreference(TYPE, getType().ordinal());
                if(stepHandler != null){
                    stepHandler.onStepFinish(IStepHandler.Step.Setup);
                }
                break;
        }
    }

    private void updateSharedPreference(String key, int value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean isSetupCompleted() {
        Log.d(TAG, "isSetupCompleted: getType(): " + getType() );
        return getType() != Type.NON;
    }

    public void setStepHandler(IStepHandler stepHandler) {
        this.stepHandler = stepHandler;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int resID) {

        if(resID == R.id.setup_watcher){
            Log.d(TAG, "onCheckedChanged: " + Type.WATCHER);
            setType(Type.WATCHER);
        }else if (resID == R.id.setup_track){
            Log.d(TAG, "onCheckedChanged: " + Type.TRACK);
            setType(Type.TRACK);
        } else {
            Log.d(TAG, "onCheckedChanged: " + Type.NON);
        }
    }

    public enum Type {
        TRACK,
        WATCHER,
        NON
    }

    public Type getType() {
        Log.d(TAG, "getType: currentType " + currentType);
        return currentType;
    }

    public void goToTypeSetup() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
