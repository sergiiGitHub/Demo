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

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends FragmentActivity implements
        View.OnClickListener,
        LocationListener,
        RequestSender.ReceiveResultListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int THREAD_NUM = 2;
    private double mLatitude = -34;
    private double mLongitude = 151;
    private RecyclerView recyclerView;
    private ItemModelAdapter modelAdapter;
    private ExecutorService executorService;
    private Handler handler;

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
        //prepareItem();

        executorService = Executors.newFixedThreadPool(THREAD_NUM);
        handler = new Handler();
    }

    private void prepareItem() {
        for ( int i = 0; i < 10; ++i ) {
            modelAdapter.appndItem(new ItemModel(modelAdapter.getItemCount(), "Item " + (i+1), "stub"));
        }
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
        final RequestSender request = new RequestSender();
        request.setReceiveResultListener(this);
        request.execute(getRequest());
    }

    private String getRequest() {
        // TODO: 07.04.16 cache latitude
        Log.d(TAG, "onClick: location=" + mLatitude + "," + mLongitude);
        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + mLatitude + "," + mLongitude );
        sb.append("&radius=5000");
        sb.append("&types=bank");
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc");//server key request
        return sb.toString();
    }

    @Override
    public void onLocationChanged(Location location) {
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
        Log.d(TAG, "onReceiveResult: " + aResult);
        JSONObject resultObject;
        try {
            resultObject = new JSONObject(aResult);
            JSONArray resultArray = resultObject.getJSONArray("results");
            Log.d(TAG, "onReceiveResult: number " + resultArray.length());
            boolean loadPhoto = true;
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
                if (photoPath != null && loadPhoto ) {
                      //JSONObject jPath = new JSONObject(aResult);
                    String ref = ((JSONObject)photoPath.get(0)).getString("photo_reference");
                    Log.d(TAG, "onReceiveResult: ref: " + ref);

                    requestBitmap(ref, item);
                    loadPhoto = false;
                }

                modelAdapter.appndItem(item);

                //Log.d(TAG, "onReceiveResult: item " + item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface BitmapListener {
        void onLoadFinish( Bitmap result );
    }

    private void doInMainThread( Runnable run ){
        handler.postDelayed( run, 100 );
    }

    private void requestBitmap(String ref, final BitmapListener aListener) {

        //GooglePlaces client = new GooglePlaces("AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc");


        StringBuilder builder = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?");
        builder.append("key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc");
        builder.append("&sensor=true");
        builder.append("&photoreference=").append(ref);
//        final String url = builder.toString();
        final String url = "https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=400" +
                "&photoreference=" +
                ref +
                //"CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU" +
                "&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc";

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = Utils.downloadImage(url);
                doInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        aListener.onLoadFinish( bitmap );
                    }
                });
            }
        });

    }

    private String getType(JSONObject itemObject) throws JSONException {
        String str = itemObject.getString("types").split(",")[0];
        return str.substring(2,str.length()-1 );
    }

}
