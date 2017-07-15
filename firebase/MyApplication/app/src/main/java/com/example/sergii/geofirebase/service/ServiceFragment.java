package com.example.sergii.geofirebase.service;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergii.geofirebase.R;
import com.example.sergii.geofirebase.utils.UtilsPermission;

/**
 * Created by sergii on 11.07.17.
 */

public class ServiceFragment extends Fragment {

    private static final String TAG = ServiceController.class.getSimpleName();
    private View.OnClickListener onClickListener;
    private Button startStopButton;
    private TextView textInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_controller, container, false);
        populateView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPermisiion();
    }

    private void checkPermisiion() {
        FragmentActivity activity = getActivity();
        if (activity == null){
            Log.e(TAG, "checkPermisiion: activity == null");
            return;
        }

        if (!UtilsPermission.hasCoursePermission(activity)){
            Toast.makeText(activity, "Can't lunch without permission, turn it on permission line", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
            return;
        }

        if (!UtilsPermission.hasAccessFineLocation(activity)) {
            Log.d(TAG, "NOT start tracking ");
            Toast.makeText(activity, "Can't lunch without permission", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            return;
        }
    }

    private void populateView(View view) {
        initButton(view);

        textInfo = view.findViewById(R.id.service_text_start_stop);;
    }

    private void initButton(View view) {
        startStopButton = view.findViewById(R.id.service_button_start_stop);
        startStopButton.setOnClickListener(onClickListener);
        if(ForegroundServices.isStarted()){
            startStopButton.setText(R.string.services_stop);
        } else {
            startStopButton.setText(R.string.services_start);
        }
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public TextView getTextInfo() {
        return textInfo;
    }

    public Button getStartStopButton() {
        return startStopButton;
    }
}
