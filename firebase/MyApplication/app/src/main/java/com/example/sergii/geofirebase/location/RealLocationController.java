package com.example.sergii.geofirebase.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sergii.geofirebase.utils.UtilsPermission;
import com.example.sergii.geofirebase.utils.UtilsTransform;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class RealLocationController implements IGeoController, LocationListener {

    private static final String TAG = RealLocationController.class.getSimpleName();
    private static final String GEO_FIRE = "geofire";
    private static final long DEFAULT_LOCATION_REQUEST = 5000;

    private GeoFire geoFire;
    private boolean isWriteGeoLocation;
    private Location location;
    private LocationManager locationManager;
    private final String email;
    private final String transformedEmail;

    public RealLocationController(Context context, String email) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(GEO_FIRE);
        geoFire = new GeoFire(ref);
        initLocationManager(context);
        this.email = email;
        this.transformedEmail = UtilsTransform.getTransformedEmail(email);
    }

    private void initLocationManager(Context context) {

        if (!UtilsPermission.hasLocationPermission(context)) {
            Log.e(TAG, "NOT start tracking ");
            Toast.makeText(context, "Can't lunch without permission", Toast.LENGTH_LONG).show();
            return;
        }
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Log.d(TAG, "initLocationManager: provider " + provider);

        // Getting Current Location From GPS
        Log.d(TAG, "start tracking");
        location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, DEFAULT_LOCATION_REQUEST, 0, this);
    }

    @Override
    public void startWriteGeoLocation() {
        Log.d(TAG, "startWriteGeoLocation: transformedEmail: " + transformedEmail);
        isWriteGeoLocation = true;
        if (location != null) {
            onLocationChanged(location);
        }
    }

    @Override
    public void stopWriteGeoLocation() {
        Log.d(TAG, "stopWriteGeoLocation: " + isWriteGeoLocation);
        isWriteGeoLocation = false;
        if(locationManager != null) {
            locationManager.removeUpdates(this);
            locationManager = null;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: 50.392223, 30.378644" + ": isWriteGeoLocation: " + isWriteGeoLocation);
        Log.d(TAG, "onLocationChanged: " + location);
        if (!isWriteGeoLocation) {
            return;
        }
        geoFire.setLocation(transformedEmail, new GeoLocation(location.getLatitude(), location.getLongitude()), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    Log.d(TAG, "onComplete: There was an error saving the location to GeoFire: " + error);
                } else {
                    Log.d(TAG, "Location saved on server successfully! key: " + key);
                }
            }
        });
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged: " + s);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled: " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled: " + s);
    }
}
