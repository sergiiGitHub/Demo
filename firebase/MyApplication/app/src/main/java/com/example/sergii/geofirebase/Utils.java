package com.example.sergii.geofirebase;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by sergii on 12.07.17.
 */

public class Utils {

    public static boolean hasLocationPermission(Context context) {
        if (!hasAccessFineLocation(context) || !hasCoursePermission(context) ){
            return false;
        }
        return true;
    }

    public static boolean hasCoursePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasAccessFineLocation(Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
