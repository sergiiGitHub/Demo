package com.example.sergii.touchcatch;

import android.content.Intent;
import android.graphics.Point;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.sergii.touchcatch.appliers.BasicApplier;
import com.example.sergii.touchcatch.appliers.PositionX;
import com.example.sergii.touchcatch.appliers.PositionY;
import com.example.sergii.touchcatch.appliers.SizeX;
import com.example.sergii.touchcatch.appliers.SizeY;
import com.example.sergii.touchcatch.filemanager.FileLineReader;
import com.example.sergii.touchcatch.filemanager.FileManager;
import com.example.sergii.touchcatch.service.MyService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_DATA = "extra_data";
    private static final String FILE_NAME = "config.txt";
    private static final String DIR_MANE = "touchCatch";
    private static final File ROOT_DIR = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DCIM);

    private Button mButton;
    private Intent mServiceIntent;
    private FileManager mFileManager;

    private File configFile;
    private File mHomeDir;
    private ValueHolder mValueHolder;
    private boolean isFirstLaunch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();
        initView();
        initValueHolder();
        initFile();
        isFirstLaunch = true;
    }

    private void initValueHolder() {
        mValueHolder = new ValueHolder();
    }

    private boolean scanConfig(File file) {
        Log.d(TAG, "parse: " + file);

        FileLineReader.OnLineReadListener onLineRead = mValueHolder.getOnLineReadListener();
        mFileManager.readFile(file, onLineRead);
        return onLineRead.getResult();
    }

    private void initFile() {
        mFileManager = new FileManager();
        mFileManager.setFileReader(new FileLineReader());

        mHomeDir = new File(ROOT_DIR, DIR_MANE);
        if ( !mHomeDir.exists() ){
            if ( !mFileManager.createDirectory( mHomeDir )){
                return;
            }
        }

        configFile = new File ( mHomeDir, FILE_NAME );
        boolean isNeedToWrite = false;
        if ( !configFile.exists() ) {
            configFile = mFileManager.createFile(this, configFile);
            isNeedToWrite = true;
        }

        if ( isNeedToWrite ){
            List<String> params = mValueHolder.getParamsList();
            mFileManager.writeToFile(configFile, params);
            params.clear();
        }
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
            if ( isFirstLaunch || scanConfig(configFile) ) {
                mServiceIntent.putExtra(EXTRA_DATA, mValueHolder.getValueHolder());
                isFirstLaunch = false;
            }
            startService(mServiceIntent);
            mButton.setText(R.string.stop);
        }
    }

}
