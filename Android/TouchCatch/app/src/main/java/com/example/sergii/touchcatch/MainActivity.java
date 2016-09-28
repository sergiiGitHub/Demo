package com.example.sergii.touchcatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.sergii.touchcatch.service.MyService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private Intent mServiceIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();
        initView();
    }

    private void initService() {
        mServiceIntent = new Intent(this, MyService.class);
    }

    private void initView() {
        View view = findViewById(R.id.root_view);
        mButton = (Button) findViewById(R.id.btnID);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: id: " + v.getId() + "; event: " + event.getAction());
                return true;
            }
        });
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick: ");

        if( MyService.isStart() ){
            stopService(mServiceIntent);
            mButton.setText(R.string.start);
        } else {
            startService(mServiceIntent);
            mButton.setText(R.string.stop);
        }
    }
}