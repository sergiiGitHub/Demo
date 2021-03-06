package com.example.sergii.geofirebase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.sergii.geofirebase.service.IServiceController;
import com.example.sergii.geofirebase.service.ServiceController;
import com.example.sergii.geofirebase.map.IMapController;
import com.example.sergii.geofirebase.map.MapController;
import com.example.sergii.geofirebase.signin.ISignIn;
import com.example.sergii.geofirebase.signin.SignInController;
import com.example.sergii.geofirebase.typesetup.TypeSetupController;

import static com.example.sergii.geofirebase.signin.SignInController.RC_SIGN_IN;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class MainController implements IStepHandler {

    private static final String TAG = MainController.class.getSimpleName();
    private final FragmentActivity activity;
    private ISignIn signIn;
    private IMapController mapController;
    private TypeSetupController typeSetupController;
    private IServiceController serviceController;

    public MainController(FragmentActivity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        setSignIn(new SignInController(activity, this));
        setMapController(new MapController(supportFragmentManager));
        setServiceController(new ServiceController(activity));
        setTypeSetupController(new TypeSetupController(activity));
        if (signIn.getUser() == null) {
            signIn.gotoSignInFragment();
        } else {
            onStepFinish(Step.SignIn);
        }
    }

    private void setServiceController(IServiceController serviceController) {
        this.serviceController = serviceController;
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

    private void setMapController(IMapController mapController) {
        this.mapController = mapController;
    }

    private void setSignIn(ISignIn signIn) {
        this.signIn = signIn;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            signIn.onActivityResult(requestCode, resultCode, data);
        }
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
            serviceController.setEmail(signIn.getUser().getEmail());
            serviceController.goServiceFragment();
        }
    }

    public void showSighIn() {
        signIn.gotoSignInFragment();
    }
}
