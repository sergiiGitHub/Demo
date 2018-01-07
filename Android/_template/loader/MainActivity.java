package com.example.sergii.demodecorationpopup;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoading();
            }
        });
    }

    private void startLoading() {
        Loader<String> loader = getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
        loader.forceLoad();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle bundle) {
        Log.d(TAG, "onCreateLoader: ");
        Loader<String> loader = null;
        if (id == LOADER_ID) {
            loader = new MyAsyncTaskLoader(this);
            //showProgress
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        Log.d(TAG, "onLoadFinished: " + s);
        // hide progress
        // show result
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(TAG, "onLoaderReset: ");
    }

    public static class MyAsyncTaskLoader extends AsyncTaskLoader<String> {

        public MyAsyncTaskLoader(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {
            Log.d(TAG, "MyAsyncTaskLoader: loadInBackground: ");
            return "finish";
        }

        @Override
        protected void onStartLoading() {
            Log.d(TAG, "MyAsyncTaskLoader: onStartLoading: ");
            super.onStartLoading();
        }
    }
}