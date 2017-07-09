package com.example.sergii.geofirebase.firstPage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergii.geofirebase.R;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class SingInOutView extends LinearLayout {

    private static final String TAG = SingInOutView.class.getSimpleName();
    private Button buttonSignInOUt;
    private TextView textInfo;

    public SingInOutView(Context context) {
        super(context);
    }

    public SingInOutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SingInOutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        Log.d(TAG, "init: ");
        buttonSignInOUt = findViewById(R.id.button_sign_in_out);
        textInfo = findViewById(R.id.text_info);
    }

    public TextView getTextInfo() {
        return textInfo;
    }

    public Button getButtonSignInOUt() {
        return buttonSignInOUt;
    }
}
