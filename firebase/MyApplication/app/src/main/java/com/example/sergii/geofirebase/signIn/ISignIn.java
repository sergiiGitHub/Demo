package com.example.sergii.geofirebase.signIn;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public interface ISignIn {
    void signIn();

    void signOut();

    FirebaseUser getUser();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
