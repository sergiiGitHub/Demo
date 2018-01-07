package com.example.sergii.demodecorationpopup;

import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.os.Message;
import android.os.health.UidHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements IHeavyTaskExecution {

    private static final String TAG = MainActivity.class.getSimpleName();

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
    public String downloadString() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Log.e(TAG, "exception",e);
        }
        return "Finish";
    }

    private void updateDate(String name) {
        Log.d(TAG, "updateDate: ");
        Button btn = (Button) findViewById(R.id.button);
        btn.setText(name);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mUpdateTask.start();
    }

    private Thread mUpdateTask = new UpdateTask(this);
    public static class UpdateTask extends Thread {

        private final MainActivity mActivityWeakReference;

        public UpdateTask(MainActivity heavyTask) {
            mActivityWeakReference = heavyTask;
        }

        @Override
        public void run() {
            Log.d(TAG, "run: start + " + Thread.currentThread());
            IHeavyTaskExecution heavyTask = mActivityWeakReference;
            String string = heavyTask.downloadString();
            heavyTask.updateUI(string);
        }
    }

    @Override
    public void updateUI(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateDate(string);
            }
        });
    }


}
