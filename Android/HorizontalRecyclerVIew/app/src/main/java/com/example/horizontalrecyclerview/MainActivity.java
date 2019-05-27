package com.example.horizontalrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PageScroller pageScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    private void initRecycler() {
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(layout);

        pageScroller = new PageScroller();
        pageScroller.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new OffsetItemDecoration(getResources().getDisplayMetrics().widthPixels));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("XXX", "onScrollStateChanged: " + getPosition());
            }
        });
    }

    private int getPosition() {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        View item = pageScroller.findSnapView(layoutManager);
        return layoutManager.getPosition(item);
    }
}
