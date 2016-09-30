package com.example.sergii.touchcatch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
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
import com.example.sergii.touchcatch.service.MyService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_DATA = "extra_data";

    private Point DEFAULT_SIZE = new Point( 100, 100 );
    private Point DEFAULT_POSITION = new Point( 492, 527 );

    private Button mButton;
    private Intent mServiceIntent;
    private HashMap< String, BasicApplier> mValueHolder;
    private MediaScannerConnection mediaScannerConnection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();
        initView();
        initValueHolder();
    }

    private void initValueHolder() {
        mValueHolder = new HashMap<>();

        addValue(new PositionX(DEFAULT_POSITION.x));
        addValue(new PositionY(DEFAULT_POSITION.y));
        addValue(new SizeX(DEFAULT_SIZE.x));
        addValue(new SizeY(DEFAULT_SIZE.y));
    }

    private void addValue(BasicApplier applier) {
        mValueHolder.put(applier.getName(), applier);
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
            //if (scanConfig()){
            scanConfig();
            mServiceIntent.putExtra(EXTRA_DATA, mValueHolder);
            //}
            startService(mServiceIntent);
            mButton.setText(R.string.stop);
        }
    }

    private boolean scanConfig() {
        File file = getFile();
        if( file != null ){
            return parse(file);
        } else {
            createFile();
            return false;
        }
    }


    private void createFile() {
        File fileDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "touchCatch");
        if (!fileDir.exists() ) {
            Log.d(TAG, "Can not find dir, create it: " + fileDir);
            if( !fileDir.mkdirs() ) {
                Log.e(TAG, "!!! Can not create directory: " + fileDir);
                return;
            }
        }

        File file = new File( fileDir, "config.txt" );
        if ( !file.exists()){
            Log.e(TAG, "File not exist: create" + fileDir);
            try {
                file.createNewFile();
                scanFile( file );
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "File can not be created " + fileDir, e);
                return;
            }
        }
        writeToFile(file);
    }

    // TODO: 01.10.16 move all file to separate class
    private void scanFile( final File file) {
         final MediaScannerConnection.MediaScannerConnectionClient mediaScannerConnectionClient =
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    @Override
                    public void onMediaScannerConnected() {
                        Log.d(TAG, "onMediaScannerConnected: " + file.getAbsolutePath());
                        if( mediaScannerConnection != null ) {
                            mediaScannerConnection.scanFile(file.getAbsolutePath(), null);
                        }
                    }

                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        mediaScannerConnection.disconnect();
                    }
                };
        mediaScannerConnection = new MediaScannerConnection(this, mediaScannerConnectionClient);
        mediaScannerConnection.connect();
    }

    private void writeToFile(File file) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);  // create an actual file & a FileWriter obj
            for( String key : mValueHolder.keySet() ){
                BasicApplier basicApplier = mValueHolder.get(key);
                if (basicApplier != null ) {
                    fw.write( basicApplier.getWriteValue() ); // write characters to
                }
            }
            fw.flush();	            // flush before closing
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "File can not be write" + file, e);
            if ( fw != null ){
                try {
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private boolean parse(File file) {
        // Get the directory for the user's public pictures directory.
        Log.d(TAG, "parse: " + file);
        FileLineReader.OnLineRead onLineRead = new FileLineReader.OnLineRead() {

            private boolean mResult = false;

            @Override
            public void read(String aString) {
                Log.d(TAG, "read: " + aString);
                Pattern p = Pattern.compile("(\\w*)=\"(\\d*)\"");
                Matcher m = p.matcher(aString);
                if (m.find()) {
                    Log.d(TAG, "read: m.group : " + m.group(1) + " " + m.group(2));
                    mResult |= setValue(m.group(1), Integer.parseInt(m.group(2)));
                }
            }

            @Override
            public int getMaxLine() {
                return mValueHolder.size();
            }

            @Override
            public boolean getResult() {
                return mResult;
            }
        };
        new FileLineReader().read(file, onLineRead);
        return onLineRead.getResult();
    }

    private boolean setValue(String key, int value) {
        BasicApplier applier = mValueHolder.get(key);
        if ( applier != null && applier.getValue() != value ){
            applier.setValue(value);
            return true;
        }
        return false;
    }

    private File getFile() {
        File fileDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "touchCatch");
        if (!fileDir.exists()) {
            Log.e(TAG, "Can not find directory: " + fileDir);
            return null;
        }

        File file = new File( fileDir, "config.txt" );
        if ( !file.exists() ){
            Log.e(TAG, "File not exist: " + fileDir);
            return null;
        }
        return file;
    }
}
