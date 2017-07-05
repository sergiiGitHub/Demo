package com.example.sergii.geofirebase.map;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import com.example.sergii.geofirebase.R;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public class MapController implements IMapController, OnMapReadyCallback, LocationCallback {

    private static final String TAG = MapController.class.getSimpleName();
    private final FragmentManager fragmentManager;
    private GoogleMap mMap;
    private Marker marker;

    public MapController(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void goToMap() {
        MapFragment mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mMapFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        MarkerOptions markerOptions = new MarkerOptions().position(sydney).title("Marker in Sydney");
        marker = mMap.addMarker(markerOptions);
        updateMapCamera();
        getLocation();
    }

    private void updateMapCamera() {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
    }

    private void getLocation() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("geofire");
        GeoFire geoFire = new GeoFire(ref);

        geoFire.getLocation( "firebase-hq", this );
    }

    @Override
    public void onLocationResult(String key, GeoLocation location) {
        Log.d(TAG, "onLocationResult: key: " + key + "; location" + location );
        LatLng latLng = new LatLng(location.latitude, location.longitude);
        Log.d(TAG, "onLocationResult: latLng");
        marker.setPosition(latLng);
        updateMapCamera();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "onCancelled: databaseError: " + databaseError );
    }
}
