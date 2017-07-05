package com.example.sergii.geofirebase.firstPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergii.geofirebase.OnCreateViewListener;
import com.example.sergii.geofirebase.R;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class StartFragment extends Fragment {

    private OnCreateViewListener listener;
    private StartPageView startPageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        startPageView = (StartPageView) inflater.inflate(R.layout.first_page_layout, container, false);
        if (listener != null ){
            listener.onViewCreated();
        }
        return startPageView;
    }

    public void update(FirebaseUser currentUser) {
        startPageView.updateUI(currentUser);
    }

    @Nullable
    public StartPageView getStartView() {
        return startPageView;
    }

    public OnCreateViewListener getListener() {
        return listener;
    }

    public void setListener(OnCreateViewListener listener) {
        this.listener = listener;
    }
}
