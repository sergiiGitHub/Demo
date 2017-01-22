package com.example.sergii.appitemview;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.sergii.appitemview.model.AppItem;
import com.example.sergii.appitemview.view.RootView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private List<AppItem> mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RootView rootView = (RootView) findViewById(R.id.root_id);

        // init two times
        //View.inflate(this, R.layout.activity_main, rootView);
        Log.d(TAG, "onCreate: rootView " + rootView );

        mItem = createItems();
        long start = System.currentTimeMillis();
        GridView grid = rootView.getGrid();
        if ( grid != null ){
            grid.setAdapter(new AppAdapter<>(mItem));
            grid.invalidate();
        }
        Log.d(TAG, "onCreate: time: " + (System.currentTimeMillis() - start) );
    }

    private List<AppItem> createItems( ) {
        int limit = 20;
        List<AppItem> result = new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent,0);
        for ( int i = 0; i < limit; ++i ){
            AppItem appItem = new AppItem();
            ResolveInfo resolveInfo = pkgAppsList.get(i);
            ApplicationInfo applicationInfo = resolveInfo.activityInfo.applicationInfo;
            appItem.setTitle( getPackageManager().getApplicationLabel(applicationInfo ).toString() );
            Log.d(TAG, "createItems: " + appItem.getTitle() );
            result.add(appItem);
        }
        return result;
    }

    public void changeMode(View v){
        Log.d(TAG, "changeMode: ");
        for ( AppItem item : mItem ){
            item.setIsEdit(!item.isEdit());
        }
    }
}
