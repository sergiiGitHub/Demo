package com.example.sergii.geofirebase.typesetup;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.sergii.geofirebase.IStepHandler;
import com.example.sergii.geofirebase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergii on 09.07.17.
 */

public class TypeSetupController implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = TypeSetupController.class.getSimpleName();
    private static final String TYPE = "SharedAppTypeControllerType";
    private static final String EMAIL = "SharedAppTypeControllerEmail";
    private final SharedPreferences sharedPreference;
    private final FragmentManager fragmentManager;
    private TypeSetupFragment fragment;

    private Type currentType;
    private String email;
    private IStepHandler stepHandler;

    public TypeSetupController(FragmentActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        sharedPreference = activity.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        currentType = Type.values()[sharedPreference.getInt(TYPE, Type.NOT_SET.ordinal())];

        email = sharedPreference.getString(EMAIL, null);

        iniFragment(activity);
    }

    private void iniFragment(Activity activity) {
        fragment = new TypeSetupFragment();
        fragment.setOnItemSelectedListener(this);
        fragment.setOnItemClickListener(this);
        fragment.setSpinnerList(createList(activity));
        fragment.setEmail(email);
    }

    private List<String> createList(Activity activity) {
        List<String> res = new ArrayList<>();
        res.add(Type.WATCHER.ordinal(), activity.getString(Type.WATCHER.getResId()));
        res.add(Type.OBSERWABLE.ordinal(), activity.getString(Type.OBSERWABLE.getResId()));
        return res;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "onItemSelected: i" + i + "; type: " + Type.values()[i]);
        setType(Type.values()[i]);
    }

    private void setType(Type type) {
        if(currentType == type){
            return;
        }
        currentType = type;
        updateSharedPreference(TYPE, currentType.ordinal());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(TAG, "onNothingSelected: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type_setup_done:
                setEmail(fragment.getEmail());
                if(stepHandler != null){
                    stepHandler.onStepFinish(IStepHandler.Step.Setup);
                }
                break;
        }
    }

    private void setEmail(String email) {
        Log.d(TAG, "setEmail: " + email);
        this.email = email;
        updateSharedPreference(EMAIL, email);
    }

    private void updateSharedPreference(String key, String value ) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void updateSharedPreference(String key, int value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean isSetupCompleted() {
        return getType() != Type.NOT_SET && email != null;
    }

    public void setStepHandler(IStepHandler stepHandler) {
        this.stepHandler = stepHandler;
    }

    // TODO: 09.07.17 spinner set in  
    public enum Type {
        WATCHER(R.string.type_setup_watcher),
        OBSERWABLE(R.string.type_setup_observable),

        NOT_SET(R.string.type_setup_non),;

        private final int resId;

        Type(int resID) {
            this.resId = resID;
        }

        public int getResId() {
            return resId;
        }
    }

    public Type getType() {
        Log.d(TAG, "getType: currentType");
        return currentType;
    }

    public void goToTypeSetup() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        // TODO: 09.07.17 add from toolbar
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
