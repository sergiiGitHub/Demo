package com.example.sergii.geofirebase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.sergii.geofirebase.signin.SignInFragment;
import com.example.sergii.geofirebase.location.IGeoController;
import com.example.sergii.geofirebase.location.RealLocationController;
import com.example.sergii.geofirebase.map.IMapController;
import com.example.sergii.geofirebase.map.MapController;
import com.example.sergii.geofirebase.signin.ISignIn;
import com.example.sergii.geofirebase.signin.SignInController;
import com.example.sergii.geofirebase.typesetup.TypeSetupController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.sergii.geofirebase.signin.SignInController.RC_SIGN_IN;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class MainController implements IStepHandler {

    private static final String TAG = MainController.class.getSimpleName();
    private final FragmentActivity activity;
    private ISignIn signIn;
    private IMapController mapController;
    private IGeoController locationController;
    private TypeSetupController typeSetupController;

    public MainController(FragmentActivity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        setSignIn(new SignInController(activity, this));
        setMapController(new MapController(activity.getSupportFragmentManager()));
        setGeoController(createLocationController());
        setTypeSetupController(new TypeSetupController(activity));
        if (signIn.getUser() == null) {
            signIn.gotoSignInFragment();
        } else {
            onStepFinish(Step.SignIn);
        }
    }

    public void showSetup() {
        typeSetupController.goToTypeSetup();
    }

    private void goToSetup() {
        if (!typeSetupController.isSetupCompleted()) {
            typeSetupController.goToTypeSetup();
        } else {
            onStepFinish(Step.Setup);
        }
    }

    private void setTypeSetupController(TypeSetupController typeSetupController) {
        this.typeSetupController = typeSetupController;
        typeSetupController.setStepHandler(this);
    }

    private IGeoController createLocationController() {
        RealLocationController realLocationController = new RealLocationController();
        realLocationController.initLocationManager(activity);
        return realLocationController;
    }

    private void setGeoController(IGeoController locationController) {
        this.locationController = locationController;
    }

    private void setMapController(IMapController mapController) {
        this.mapController = mapController;
    }

    private void setSignIn(ISignIn signIn) {
        this.signIn = signIn;
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

    public void onPermissionGranted() {
        locationController.initLocationManager(activity);
    }

    @Override
    public void onStepFinish(Step step) {
        switch (step){
            case SignIn:
                goToSetup();
                break;
            case Setup:
                goToMainView();
                break;
        }
    }

    private void goToMainView() {
        if(typeSetupController.getType() == TypeSetupController.Type.WATCHER){
            mapController.goToMap();
        } else {
            // TODO: 09.07.17 add service start stop
        }
    }

    public void showSighIn() {
        signIn.gotoSignInFragment();
    }
}
