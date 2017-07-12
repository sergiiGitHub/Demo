package com.example.sergii.geofirebase.service;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sergii.geofirebase.R;

/**
 * Created by sergii on 11.07.17.
 */

public class ServiceFragment extends Fragment {

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

    private void populateView(View view) {
        startStopButton = view.findViewById(R.id.service_button_start_stop);
        startStopButton.setOnClickListener(onClickListener);

        textInfo = view.findViewById(R.id.service_text_start_stop);
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
