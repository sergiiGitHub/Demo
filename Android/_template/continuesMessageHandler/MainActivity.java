package com.example.sergii.demodecorationpopup;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

// Handler Thread
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private HandleMessageLoop handleMessageLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleMessageLoop = new HandleMessageLoop();
        handleMessageLoop.start();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = handleMessageLoop.getHandler().obtainMessage(HandleMessageLoop.DOWNLOAD);
                msg.sendToTarget();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stop looper
        handleMessageLoop.getLooper().quit();
    }

    public static class HandleMessageLoop extends Thread {

        public static final int DOWNLOAD = 0;
        private Handler handler;

        @Override
        public void run() {
            Looper.prepare();
            //only message work
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.d(TAG, "handleMessage: ");
                    if (msg.what == DOWNLOAD){
                        Log.d(TAG, "handleMessage: download");
                    }
                }
            };
            // make infinitive loop
            Looper.loop();
        }

        public Handler getHandler() {
            return handler;
        }

        public Looper getLooper() {
            return Looper.getMainLooper();
        }
    }
}
