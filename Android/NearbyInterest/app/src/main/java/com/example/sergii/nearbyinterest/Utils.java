package com.example.sergii.nearbyinterest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sergii on 10.04.16.
 */
public  class  Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static Bitmap downloadImage(String strUrl)  {
        Bitmap bitmap = null;
        InputStream iStream = null;
        try{
            URL url = new URL(strUrl);

            /** Creating an http connection to communcate with url */
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            /** Connecting to url */
            urlConnection.connect();
            /** Reading data from url */
            iStream = urlConnection.getInputStream();
            /** Creating a bitmap from the stream returned from the url */
            bitmap = BitmapFactory.decodeStream(iStream);

        }catch(Exception e){
            Log.d(TAG, "exception", e);
        }finally{
            try {
                if ( iStream != null ) {
                    iStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

}
