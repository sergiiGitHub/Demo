package com.example.sergii.geofirebase.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

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
    private GeoFire geoFire;
    private boolean isWriteGeoLocation;
    private Location location;

    public RealLocationController(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("geofire");
        geoFire = new GeoFire(ref);
    }

    public void initLocationManager(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "NOT start tracking ");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            return;// TODO: 10.04.16 ask

        }
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Log.d(TAG, "initLocationManager: provider " + provider );

        // Getting Current Location From GPS
        Log.d(TAG, "start tracking");
        location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 5000, 0, this);
    }

    @Override
    public void writeGeoLocation() {
        isWriteGeoLocation = true;
        if (location != null){
            onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: 50.392223, 30.378644");
        Log.d(TAG, "onLocationChanged: " + location + ": isWriteGeoLocation: " + isWriteGeoLocation);
        if (!isWriteGeoLocation){
            return;
        }
        geoFire.setLocation("firebase-hq", new GeoLocation(location.getLatitude(), location.getLongitude()), new GeoFire.CompletionListener() {
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
