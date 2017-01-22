package com.example.sergii.appitemview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.sergii.appitemview.R;

/**
 * Created by sergii on 22.01.17.
 */

public class RootView extends LinearLayout{

    private static final String TAG = RootView.class.getSimpleName();
    private GridView gridView;

    public RootView(Context context) {
        this(context, null);
    }

    public RootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        gridView = (GridView) findViewById(R.id.grid);
        Log.d(TAG, "init: gridView: " + gridView);
    }

    public GridView getGrid() {
        return gridView;
    }
}
