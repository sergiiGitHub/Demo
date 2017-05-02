package com.example.sergii.recycleviewcolumnchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;

import com.example.sergii.recycleviewcolumnchange.view.Adapter;
import com.example.sergii.recycleviewcolumnchange.view.GridRecyclerView;
import com.example.sergii.recycleviewcolumnchange.view.Model;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static int DEFAULT_COLUMN_COUNT = 3;
    private GridLayoutManager gridLayoutManager;
    private Adapter adapter;
    private GridRecyclerView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new Adapter();
        for (int i = 0; i < 10; ++i){
            adapter.addItem(new Model(Integer.toString(i)));
        }

        gridLayoutManager = new GridLayoutManager(this, DEFAULT_COLUMN_COUNT);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return DEFAULT_COLUMN_COUNT;
//            }
//        });
        grid = (GridRecyclerView) findViewById(R.id.image_grid_view);
        grid.setLayoutManager(gridLayoutManager);
        grid.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            Log.d(TAG, "onKeyUp: ");
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount() + 1);
            //++DEFAULT_COLUMN_COUNT;

        }else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            //++DEFAULT_COLUMN_COUNT;
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount() - 1);
            Log.d(TAG, "onKeyDown: ");
        }
//        grid.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter.notifyItemRangeChanged(0 , adapter.getItemCount());
//            }
//        }, 100);
        return true;
        //return super.onKeyDown(keyCode, event);
    }
}
