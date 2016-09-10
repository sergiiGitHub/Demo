package com.example.sergii.nearbyinterest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


/**
 * Created by sergii on 10.04.16.
 */
public class LocationTracker implements LocationListener {
    private static final String TAG = LocationTracker.class.getSimpleName();

    private boolean isLocationChange = true;
    private Location location;

    public void startLocationTracking( Context aContext ) {
        if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "NOT start tracking ");
            return;// TODO: 10.04.16 ask
        }

        LocationManager locationManager = (LocationManager) aContext.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location From GPS
        Log.d(TAG, "start tracking");
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(provider, 20000, 0, this);
    }

    @Override
    public void onLocationChanged(Location aLocation) {
        isLocationChange = true;
        location = aLocation;
        Log.d(TAG, "onLocationChanged: " + location.getLatitude() + ";  " + location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public boolean isLocationChange() {
        return isLocationChange && location != null;
    }

    public Location getCurrentLocation() {
        isLocationChange = false;
        return location;
    }
}
