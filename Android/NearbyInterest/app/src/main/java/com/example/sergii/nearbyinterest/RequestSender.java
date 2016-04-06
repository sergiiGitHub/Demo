package com.example.sergii.nearbyinterest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sergii on 06.04.16.
 */
public class RequestSender extends AsyncTask<String, Integer, String> {

    public interface ReceiveResultListener{
        void onReceiveResult( String aResult );
    }

    private ReceiveResultListener receiveResultListener;

    private static final String TAG = RequestSender.class.getSimpleName();
    String data = null;

    @Override
    protected String doInBackground(String... url) {
        if ( getReceiveResultListener()  == null){
            return data;
        }
        try{
            data = downloadUrl(url[0]);
        }catch(Exception e){
            Log.d(TAG, "Background Task", e);
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result){
        Log.d(TAG, "onPostExecute: result" + result);
        getReceiveResultListener().onReceiveResult(result);
    }

    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb  = new StringBuffer();

            String line;
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        }catch(Exception e){
            Log.d(TAG, e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }


    public void setReceiveResultListener(ReceiveResultListener receiveResultListener) {
        this.receiveResultListener = receiveResultListener;
    }

    public ReceiveResultListener getReceiveResultListener() {
        return receiveResultListener;
    }


}
