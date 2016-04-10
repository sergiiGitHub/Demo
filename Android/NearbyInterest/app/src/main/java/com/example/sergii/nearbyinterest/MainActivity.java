package com.example.sergii.nearbyinterest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends FragmentActivity implements
        View.OnClickListener,
        LocationListener,
        RequestSender.ReceiveResultListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final StringBuilder SB_REQUEST_PHOTO = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?")
            .append("maxwidth=200")// TODO: 10.04.16 change
            .append("&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc")
            .append("&photoreference=");

    private final StringBuilder SB_REQUEST_PALCE = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
        .append("radius=5000")
        .append("&sensor=true")
        .append("&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc");//server key request
    private static final int THREAD_NUM = 2;
    private double mLatitude = -34;
    private double mLongitude = 151;
    private RecyclerView recyclerView;
    private ItemModelAdapter modelAdapter;
    private ExecutorService executorService;
    private Handler handler;
    private boolean isLocationChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        startLocationTracking();

    }

    private void init() {

        findViewById(R.id.id_button).setOnClickListener(this);
        modelAdapter = new ItemModelAdapter(new ArrayList<ItemModel>());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(modelAdapter);

        executorService = Executors.newFixedThreadPool(THREAD_NUM);
        handler = new Handler();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void startLocationTracking() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "NOT start tracking ");
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
    public void onClick(View v) {
        final String string = getRequest();
        if ( string != null ){
            final RequestSender request = new RequestSender();
            request.setReceiveResultListener(this);
            request.execute(string);
        }
    }

    private String getRequest() {

        if ( !isLocationChange ){
            return null;
        }
        Log.d(TAG, "onClick: location=" + mLatitude + "," + mLongitude);
        return new StringBuilder(SB_REQUEST_PALCE.toString())
                .append("&types=food")
                .append("&location=")
                .append(mLatitude)
                .append(",")
                .append(mLongitude)
                .toString();
    }

    @Override
    public void onLocationChanged(Location location) {
        isLocationChange = true;
        Log.d(TAG, "onLocationChanged: " + location.getLatitude() + ";  " + location.getLongitude());
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
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

    @Override
    public void onReceiveResult(String aResult) {
        //// TODO: 07.04.16 in work thread
        // check time
        Log.d(TAG, "onReceiveResult: " + aResult);
        JSONObject resultObject;
        try {
            resultObject = new JSONObject(aResult);
            JSONArray resultArray = resultObject.getJSONArray("results");
            Log.d(TAG, "onReceiveResult: number " + resultArray.length());
            for ( int i = 0; i < resultArray.length(); ++i ) {
                JSONObject itemObject = resultArray.getJSONObject(i);

                String name = null;
                String type = null;
                JSONArray photoPath = null;
                try {
                    name = itemObject.getString("name");
                    type = getType(itemObject);
                    photoPath = itemObject.getJSONArray("photos");
//                    photoPath = itemObject.getJSONObject("photos");// every time no photo
                } catch (JSONException e) {
                    Log.e(TAG, "no photo");
                }
                ItemModel item = new ItemModel( modelAdapter.getItemCount(), name, type);
                if (photoPath != null ) {
                    String ref = ((JSONObject)photoPath.get(0)).getString("photo_reference");
                    String width = ((JSONObject)photoPath.get(0)).getString("width");

                    Log.d(TAG, "onReceiveResult: name: " + name + "; width: " + width);
                    item.setReference(ref);
                    requestBitmap(item);
                }
                modelAdapter.appndItem(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface BitmapListener {
        void onLoadFinish( Bitmap result );
        String getReference();
    }

    private void doInMainThread( Runnable run ){
        handler.post(run);
    }

    private void requestBitmap(final BitmapListener aListener) {
        executorService.execute(new BitmapLoader(aListener));
    }

    private String getType(JSONObject itemObject) throws JSONException {
        String str = itemObject.getString("types").split(",")[0];
        return str.substring(2,str.length()-1 );
    }

    class BitmapLoader implements Runnable {

        private final WeakReference< BitmapListener > listener;
        private final String url;

        private BitmapLoader( BitmapListener aListener ){
            listener = new WeakReference<>(aListener);
            url = new StringBuilder(SB_REQUEST_PHOTO.toString())
                    .append(aListener.getReference()).toString();
        }

        @Override
        public void run() {
            final Bitmap bitmap = Utils.downloadImage(url);
            doInMainThread(new Runnable() {
                @Override
                public void run() {
                    listener.get().onLoadFinish(bitmap);
                }
            });
        }
    }
}
