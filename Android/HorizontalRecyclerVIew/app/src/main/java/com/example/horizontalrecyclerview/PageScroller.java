package com.example.horizontalrecyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Sergii Muzychuk (sergii.muzychuk@globallogic.com) on 27.05.19.
 */
public class PageScroller extends PagerSnapHelper {

    private static final String TAG = PageScroller.class.getSimpleName();

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int res[] = super.calculateDistanceToFinalSnap(layoutManager, targetView);
        Log.d(TAG, "calculateDistanceToFinalSnap: " + res[0]);
        return res;
    }
}