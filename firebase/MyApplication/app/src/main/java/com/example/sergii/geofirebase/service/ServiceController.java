package com.example.sergii.geofirebase.service;

import android.content.Intent;
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
    public interface ACTION {
        String START_FOREGROUND_ACTION = ServiceController.class.getPackage() + "." +  TAG + ".startforeground";
        String STOP_FOREGROUND_ACTION = ServiceController.class.getPackage() + "." +  TAG + ".startforeground";
        String MAIN_ACTION = ServiceController.class.getPackage() + "." +  TAG + ".main";
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
    public void onClick(View view) {
        if (view.getId() == R.id.service_button_start_stop){
            startService();
        }
    }

    private void startService() {
        Log.d(TAG, "startService: ");
        Intent startIntent = new Intent(activity, ForegroundServices.class);
        startIntent.setAction(ACTION.START_FOREGROUND_ACTION);
        activity.startService(startIntent);
    }
}
