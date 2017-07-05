package com.example.sergii.geofirebase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.example.sergii.geofirebase.firstPage.StartFragment;
import com.example.sergii.geofirebase.firstPage.StartPageView;
import com.example.sergii.geofirebase.location.IGeoController;
import com.example.sergii.geofirebase.location.LocationController;
import com.example.sergii.geofirebase.location.RealLocationController;
import com.example.sergii.geofirebase.map.IMapController;
import com.example.sergii.geofirebase.map.MapController;
import com.example.sergii.geofirebase.signIn.ISignIn;
import com.example.sergii.geofirebase.signIn.SignIn;
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
    private IMapController mapController;
    private IGeoController locationController;

    public MainController(FragmentActivity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        setSignIn(new SignIn(activity, this));
        setMapController(new MapController(activity.getFragmentManager()));
        setGeoController(new RealLocationController());
        gotoStartFragment();
    }

    private void setGeoController(IGeoController locationController) {
        this.locationController = locationController;
        this.locationController.initLocationManager(activity);
    }

    private void setMapController(IMapController mapController) {
        this.mapController = mapController;
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
                locationController.writeGeoLocation();
                break;
            case R.id.go_to_map:
                mapController.goToMap();
                break;
        }
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

    public void onPermissionGranted() {
        locationController.initLocationManager(activity);
    }
}
