package com.example.sergii.geofirebase.service;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.sergii.geofirebase.R;

/**
 * Created by sergii on 11.07.17.
 */

public class ServiceController implements IServiceController, View.OnClickListener {

    private static final String TAG = ServiceController.class.getSimpleName();
    private final FragmentManager fragmentManager;
    private final FragmentActivity activity;
    private ServiceFragment fragment;
    private String email;

    public interface ACTION {
        String START_FOREGROUND_ACTION = "start";
        String STOP_FOREGROUND_ACTION = "stop";

        String MAIN_ACTION = ServiceController.class.getPackage() + "." +  TAG + ".main";
    }

    public interface EXTRA{
        String TRACK_EMAIL = "email";
    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }

    public ServiceController(FragmentActivity activity){
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
        fragment = new ServiceFragment();
        fragment.setOnItemClickListener(this);
    }

    @Override
    public void goServiceFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setEmail(String email) {
        Log.d(TAG, "setEmail: " + email);
        this.email = email;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.service_button_start_stop){
            if(ForegroundServices.isStarted()){
                stopServices();
            } else {
                startService();
            }
        }
    }

    private void stopServices() {
        Log.d(TAG, "stopServices: ");
        Intent stopIntent = new Intent(activity, ForegroundServices.class);
        stopIntent.setAction(ACTION.STOP_FOREGROUND_ACTION);
        activity.startService(stopIntent);

        updateButton(true);
    }

    private void startService() {
        Log.d(TAG, "startService: ");
        Intent startIntent = new Intent(activity, ForegroundServices.class);
        startIntent.setAction(ACTION.START_FOREGROUND_ACTION);
        startIntent.putExtra(EXTRA.TRACK_EMAIL, email);
        // TODO: 13.07.17 use own model
        activity.startService(startIntent);
        updateButton(false);
    }

    private void updateButton(boolean isStopped) {
        if(isStopped){
            fragment.getStartStopButton().setText(R.string.services_start);
        } else {
            fragment.getStartStopButton().setText(R.string.services_stop);
        }
    }
}
