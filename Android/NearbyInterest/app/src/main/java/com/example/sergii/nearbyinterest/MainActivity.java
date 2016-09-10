package com.example.sergii.nearbyinterest;

import android.location.Location;
import android.os.Bundle;
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

public class MainActivity extends FragmentActivity implements
        View.OnClickListener,
        RequestSender.ReceiveResultListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final StringBuilder SB_REQUEST_PLACE = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
        .append("radius=5000")
        .append("&sensor=true")
        .append("&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc");//server key request

    private RecyclerView recyclerView;
    private ItemModelAdapter modelAdapter;
    private LocationTracker location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        findViewById(R.id.id_button).setOnClickListener(this);
        modelAdapter = new ItemModelAdapter( this, new ArrayList<ItemModel>());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(modelAdapter);

        location = new LocationTracker();
        location.startLocationTracking(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

//        if ( !location.isLocationChange() ){
//            return null;
//        }
        Location loc = location.getCurrentLocation();
        double mLatitude = -34;
        double mLongitude = 151;
        if( loc != null ){
            mLatitude = loc.getLatitude();
            mLongitude = loc.getLongitude();
        }
        Log.d(TAG, "onClick: location=" + mLatitude + "," + mLongitude);
        return new StringBuilder(SB_REQUEST_PLACE.toString())
                .append("&types=food")
                .append("&location=")
                .append(mLatitude)
                .append(",")
                .append(mLongitude)
                .toString();
    }

    //Parser
    @Override
    public void onReceiveResult(String aResult) {
        //// TODO: 07.04.16 in work thread
        // TODO write unit test
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
                }
                modelAdapter.appendItem(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getType(JSONObject itemObject) throws JSONException {
        String str = itemObject.getString("types").split(",")[0];
        return str.substring(2,str.length()-1 );
    }
}
