package com.example.horizontalrecyclerview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Sergii Muzychuk (sergii.muzychuk@globallogic.com) on 27.05.19.
 */
public class OffsetItemDecoration extends RecyclerView.ItemDecoration {

    private final int mScreenWidth;

    public OffsetItemDecoration(int screenWidth) {
        mScreenWidth = screenWidth;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int offset = (mScreenWidth - view.getLayoutParams().width) / 2;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (parent.getChildAdapterPosition(view) == 0) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin = 0;
            setupOutRect(outRect, offset, true);
        } else if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin = 0;
            setupOutRect(outRect, offset, false);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }

    private void setupOutRect(Rect rect, int offset, boolean start) {
        if (start) {
            rect.left = offset;
        } else {
            rect.right = offset;
        }
    }
}