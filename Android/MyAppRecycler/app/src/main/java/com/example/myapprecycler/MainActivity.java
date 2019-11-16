package com.example.myapprecycler;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.example.myapprecycler.utils.HalfZoomLayout;
import com.example.myapprecycler.utils.StartSnapHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyAdapterSimple adapter;
    private int increment = 0;
    private RecyclerView recyclerView;
    private HalfZoomLayout layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        initButton();
    }

    private void initButton() {
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAndScroll();
            }
        });

        findViewById(R.id.scale).setOnClickListener(new View.OnClickListener() {
            private boolean isHalf = false;

            @Override
            public void onClick(View v) {
                isHalf = !isHalf;
                layoutManager.setIsHalf(isHalf);
                recyclerView.requestLayout();
            }
        });
    }

    private void addAndScroll() {
        adapter.add(1, createData());
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {
            @Override protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(1);

        layoutManager.startSmoothScroll(smoothScroller);
    }

    private void initRecyclerView() {

        layoutManager = new HalfZoomLayout(this);

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new MyAdapterSimple(createArray());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private ArrayList<MyData> createArray() {
        int size = 5;
        ArrayList<MyData> datas = new ArrayList<>(size);

        for (int i = 0; i < size; ++i) {
            datas.add(createData());
        }
        return datas;
    }

    private MyData createData() {
        ++increment;
        return new MyData("text" + increment);
    }

}
