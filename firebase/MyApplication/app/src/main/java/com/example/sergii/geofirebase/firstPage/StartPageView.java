package com.example.sergii.geofirebase.firstPage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergii.geofirebase.R;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class StartPageView extends LinearLayout {

    private static final String TAG = StartPageView.class.getSimpleName();
    private Button buttonSignOut;
    private Button buttonSignIn;
    private TextView textInfo;
    private Button buttonWriteData;
    private Button buttonGeoLocation;
    private Button buttonGoToMap;

    public StartPageView(Context context) {
        super(context);
    }

    public StartPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StartPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        Log.d(TAG, "init: ");
        buttonSignIn = findViewById(R.id.button_sign_in);
        //buttonSignIn.setOnClickListener(this);

        buttonSignOut = findViewById(R.id.button_sign_out);
        //buttonSignOut.setOnClickListener(this);

        buttonWriteData = findViewById(R.id.write_data);
        //buttonWriteData.setOnClickListener(this);

        buttonGeoLocation = findViewById(R.id.write_geo_location);
        //buttonGeoLocation.setOnClickListener(this);

        buttonGoToMap = findViewById(R.id.go_to_map);
        //buttonGoToMap.setOnClickListener(this);

        textInfo = findViewById(R.id.text_info);
    }



    public Button getButtonGeoLocation() {
        return buttonGeoLocation;
    }

    public Button getButtonGoToMap() {
        return buttonGoToMap;
    }

    public Button getButtonSignIn() {
        return buttonSignIn;
    }

    public Button getButtonSignOut() {
        return buttonSignOut;
    }

    public Button getButtonWriteData() {
        return buttonWriteData;
    }

    public TextView getTextInfo() {
        return textInfo;
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            if (textInfo == null){
                return;
            }
            textInfo.setText("Hello " + user.getDisplayName());

            buttonSignIn.setEnabled(false);
            buttonSignIn.setAlpha(0.5f);

            buttonSignOut.setEnabled(true);
            buttonSignOut.setAlpha(1.f);

            buttonWriteData.setEnabled(true);
            buttonWriteData.setAlpha(1.f);

            buttonGeoLocation.setEnabled(true);
            buttonGeoLocation.setAlpha(1.f);
        } else {
            textInfo.setText("FAIL !!!");

            buttonSignOut.setEnabled(false);
            buttonSignOut.setAlpha(0.5f);

            buttonSignIn.setEnabled(true);
            buttonSignIn.setAlpha(1.f);

            buttonWriteData.setEnabled(false);
            buttonWriteData.setAlpha(0.5f);

            buttonGeoLocation.setEnabled(false);
            buttonGeoLocation.setAlpha(0.5f);
        }
    }
}
