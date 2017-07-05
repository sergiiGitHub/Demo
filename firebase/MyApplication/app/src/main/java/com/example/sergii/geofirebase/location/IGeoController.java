package com.example.sergii.geofirebase.location;

import android.app.Activity;

/**
 * Created by s.muzychuk on 7/5/2017.
 */

public interface IGeoController {
    void writeGeoLocation();

    void initLocationManager(Activity activity);
}
