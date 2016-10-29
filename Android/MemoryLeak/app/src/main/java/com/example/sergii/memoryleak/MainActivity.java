package com.example.sergii.memoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ArrayWrapper> mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArrayList();
    }

    private void initArrayList() {
        int size = 3;
        if ( mArray != null ){
            //destroy();
        }
        mArray = new ArrayList<>(size);
        for ( int i = 0; i < size; i++ ){
            mArray.add(new ArrayWrapper(i));
        }
    }

    private void destroy() {
        for( ArrayWrapper aw : mArray ){
            aw.destroy();
        }
    }

    public void startLeak(View v){
        initArrayList();
    }
}