package com.example.sergii.geofirebase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.sergii.geofirebase.firstPage.StartFragment;
import com.example.sergii.geofirebase.firstPage.StartPageView;
import com.example.sergii.geofirebase.signIn.ISignIn;
import com.example.sergii.geofirebase.signIn.SignIn;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.sergii.geofirebase.signIn.SignIn.RC_SIGN_IN;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class MainController implements View.OnClickListener,
        SignIn.ISignInListener, OnCreateViewListener {

    private static final String TAG = MainController.class.getSimpleName();
    private final FragmentActivity activity;
    private StartFragment startFragment;
    private ISignIn signIn;

    public MainController(FragmentActivity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        setSignIn(new SignIn(activity, this));
        gotoStartFragment();
    }

    private void setSignIn(ISignIn signIn) {
        this.signIn = signIn;
    }

    private void gotoStartFragment() {
        startFragment = new StartFragment();
        startFragment.setListener(this);
        if (activity.findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            // Create a new Fragment to be placed in the activity layout
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            startFragment.setArguments(activity.getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, startFragment).commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_sign_in:
                signIn.signIn();
                break;
            case R.id.button_sign_out:
                signIn.signOut();
                break;
            case R.id.write_data:
                writeData();
                break;

            case R.id.write_geo_location:
                writeGeoLocation();
                break;
            case R.id.go_to_map:
                goToMap();
                break;
        }
    }


    private void writeGeoLocation() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("geofire");
        GeoFire geoFire = new GeoFire(ref);

        geoFire.setLocation("firebase-hq", new GeoLocation(37.7853889, -122.4056973), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    Log.d(TAG, "onComplete: There was an error saving the location to GeoFire: " + error);
                } else {
                    Log.d(TAG, "Location saved on server successfully!");
                }
            }
        });
    }

    private void writeData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!2");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void goToMap() {
// Create fragment and give it an argument specifying the article it should show
        MapFragment newFragment = new MapFragment();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            signIn.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void update(FirebaseUser currentUser) {
        startFragment.update(currentUser);
    }

    @Override
    public void onViewCreated() {
        populate();
        update(FirebaseAuth.getInstance().getCurrentUser());

    }

    private void populate() {
        StartPageView view = startFragment.getStartView();
        view.getButtonGeoLocation().setOnClickListener(this);
        view.getButtonGoToMap().setOnClickListener(this);
        view.getButtonSignIn().setOnClickListener(this);
        view.getButtonSignOut().setOnClickListener(this);
        view.getButtonWriteData().setOnClickListener(this);
    }
}
