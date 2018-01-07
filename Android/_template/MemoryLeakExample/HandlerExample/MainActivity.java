package com.example.sergii.demodecorationpopup;

import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animatable drawable = (Animatable) ((ImageView) findViewById(R.id.image)).getDrawable();
        final Animatable drawable2 = (Animatable) (findViewById(R.id.text)).getBackground();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable.start();
                drawable2.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myHandler = new MyHandler();
        startUpdate();
    }

    private void startUpdate() {
        new Thread(new MyRunnable(myHandler)).start();
    }

    public static class MyRunnable implements Runnable {

        private WeakReference<MyHandler> weakReference;

        public MyRunnable (MyHandler myHandler) {
            weakReference = new WeakReference<>(myHandler);
        }

        @Override
        public void run() {
            localShowProgress();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Log.d(TAG, "run: exception", e);
            }

            localUpdate("finish");
        }

        private void localUpdate(String str) {
            MyHandler handler = weakReference.get();
            if (handler != null) {
                Message msg = handler.obtainMessage(MyHandler.UPDATE_DATA, str);
                handler.sendMessage(msg);
                handler.sendEmptyMessage(MyHandler.HIDE_PROGRESS);
            }
        }

        private void localShowProgress() {
            MyHandler handler = weakReference.get();
            if (handler != null) {
                handler.sendEmptyMessage(MyHandler.SHOW_PROGRESS);
            }
        }
    }

    public class MyHandler extends Handler {

        public static final int SHOW_PROGRESS = 0;
        public static final int HIDE_PROGRESS = 1;
        public static final int UPDATE_DATA = 2;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_PROGRESS:
                    showProgress();
                    break;
                case HIDE_PROGRESS:
                    hideProgress();
                    break;
                case UPDATE_DATA:
                    updateDate((String)msg.obj);
                    break;
            }
        }
    }

    private void updateDate(String name) {
        Log.d(TAG, "updateDate: ");
        Button btn = (Button) findViewById(R.id.button);
        btn.setText(name);
    }

    private void hideProgress() {
        Log.d(TAG, "hideProgress: ");
    }

    private void showProgress() {
        Log.d(TAG, "showProgress: ");
    }

}