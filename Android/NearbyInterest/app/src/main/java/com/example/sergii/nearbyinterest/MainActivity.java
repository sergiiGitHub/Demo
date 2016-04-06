package com.example.sergii.nearbyinterest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.sergii.nearbyinterest.recycleview_provider.ItemModel;
import com.example.sergii.nearbyinterest.recycleview_provider.ItemModelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements
        View.OnClickListener,
        LocationListener,
        RequestSender.ReceiveResultListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private double mLatitude = -34;
    private double mLongitude = 151;
    private RecyclerView recyclerView;
    private List<ItemModel> modelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //startLocationTracking();
        findViewById(R.id.id_button).setOnClickListener(this);

        modelItems = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //prepareItem();
        udpateView();
    }

    private void prepareItem() {
        for ( int i = 0; i < 10; ++i ) {
            modelItems.add(new ItemModel("Item " + (i+1)));
        }
    }

    // TODO: 06.04.16
    private void startLocationTracking() {
        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location From GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(provider, 20000, 0, this);
    }

    @Override
    public void onClick(View v) {
        final RequestSender request = new RequestSender();
        request.setReceiveResultListener(this);
        request.execute(getRequest());
    }

    private String getRequest() {
        // TODO: 07.04.16 cache latitude
        Log.d(TAG, "onClick: location=" + mLatitude + "," + mLongitude);
        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location="+mLatitude+","+mLongitude);
        sb.append("&radius=5000");
        sb.append("&types=bank");
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc");
        return sb.toString();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }d

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onReceiveResult(String aResult) {
        //// TODO: 07.04.16 in work thread
        Log.d(TAG, "onReceiveResult: " + aResult);
        JSONObject resultObject = null;
        try {
            resultObject = new JSONObject(aResult);
            JSONArray resultArray = resultObject.getJSONArray("results");
            Log.d(TAG, "onReceiveResult: number " + resultArray.length());
            for ( int i = 0; i < resultArray.length(); ++i ){
                JSONObject itemObject = resultArray.getJSONObject(i);
                String str = itemObject.getString("name");
                modelItems.add(new ItemModel(str));

                Log.d(TAG, "onReceiveResult: item " + str );
            }
            udpateView();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void udpateView() {
        recyclerView.setAdapter( new ItemModelAdapter(modelItems) );
    }

}
