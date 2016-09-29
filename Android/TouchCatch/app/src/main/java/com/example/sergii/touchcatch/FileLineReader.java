package com.example.sergii.touchcatch;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by sergii on 29.09.16.
 */
public class FileLineReader {

    private static final String TAG = FileLineReader.class.getSimpleName();

    public interface OnLineRead{
        void read( String aString );
        int getMaxLine();
    }

    public void read( File file, OnLineRead aOnLineRead ){

        if ( file == null || aOnLineRead == null ){
            return;
        }
        int lineRead = 0;
        BufferedReader br = null;
        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            br  = new BufferedReader(fr);
            String s;
            while( (s = br.readLine()) != null && lineRead < aOnLineRead.getMaxLine() ){
                Log.d(TAG, "parse: " + s);
                ++lineRead;
                aOnLineRead.read(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch ( IOException e ){
            e.printStackTrace();
        } finally {
            if ( br != null ){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
