package com.example.sergii.geofirebase.signin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sergii.geofirebase.IStepHandler;
import com.example.sergii.geofirebase.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class SignInController implements GoogleApiClient.OnConnectionFailedListener, ISignIn, View.OnClickListener {

    public static final int RC_SIGN_IN = 9001;
    private static final String TAG = SignInController.class.getSimpleName();

    private final FragmentActivity activity;
    private GoogleApiClient mGoogleApiClient;

    private IStepHandler stepHandler;
    private SignInFragment signInFragment;

    public SignInController(FragmentActivity activity, @NonNull IStepHandler stepHandler) {
        this.activity = activity;
        setStepHandler(stepHandler);
        buildSignInOption();
        initFragment();
    }

    private void initFragment() {
        signInFragment = new SignInFragment();
        signInFragment.setCurrentUser(FirebaseAuth.getInstance().getCurrentUser());
        signInFragment.setOnClickListener(this);
    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                setCurrentUser(null);
            }
        });
    }

    @Override
    public FirebaseUser getUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    private void buildSignInOption() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity/* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult: isSuccess: " + result.isSuccess());
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                setCurrentUser(null);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            setCurrentUser(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            setCurrentUser(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void setCurrentUser(FirebaseUser user) {
        signInFragment.setCurrentUser(user);
        if(user != null && stepHandler != null){
            stepHandler.onStepFinish(IStepHandler.Step.SignIn);
        }
    }

    public void setStepHandler(IStepHandler stepHandler ) {
        this.stepHandler = stepHandler;
    }

    @Override
    public void onClick(View view) {
        if ( view.getId() == R.id.button_sign_in_out ){
            if(signInFragment.getCurrentUser() == null){
                signIn();
            } else {
                signOut();
            }
        }
    }

    @Override
    public void gotoSignInFragment() {
        if (activity.findViewById(R.id.fragment_container) != null) {

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
            if(supportFragmentManager.getFragments().isEmpty()){
                supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, signInFragment).commit();
            } else {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, signInFragment);
                // TODO: 09.07.17 add from toolbar
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
    }

}
