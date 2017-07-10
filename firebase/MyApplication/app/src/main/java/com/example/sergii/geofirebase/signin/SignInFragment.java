package com.example.sergii.geofirebase.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergii.geofirebase.R;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class SignInFragment extends Fragment {

    private SingInOutView signInOutView;
    private FirebaseUser currentUser;
    private View.OnClickListener onClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        signInOutView = (SingInOutView) inflater.inflate(R.layout.first_page_layout, container, false);
        populateView();
        return signInOutView;
    }

//    @Override
//    public void onDestroyView() {
//        ViewGroup mContainer = getActivity().findViewById(R.id.fragment_container);
//        mContainer.removeAllViews();
//        super.onDestroyView();
//    }

    private void populateView() {
        updateUI();
        signInOutView.getButtonSignInOUt().setOnClickListener(getOnClickListener());
    }

    private void updateUI() {
        if (currentUser != null) {
            signInOutView.getTextInfo().setText("Hello " + currentUser.getDisplayName());
            signInOutView.getButtonSignInOUt().setText("Sign out");
        } else {
            signInOutView.getTextInfo().setText("FAIL !!!");
            signInOutView.getButtonSignInOUt().setText("Sign in");
        }
    }

    @Nullable
    public SingInOutView getStartView() {
        return signInOutView;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
        if (signInOutView != null){
            updateUI();
        }
    }


    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }
}
