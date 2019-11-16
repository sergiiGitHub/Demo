package com.example.myapprecycler.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class HalfZoomLayout extends LinearLayoutManager {

    private boolean mIsHalfMode = false;

    private static final float SCALE_FACTOR = 0.5f;
    private static final float MIN_SCALE_HEIGHT = 0.75f;

    public HalfZoomLayout(Context context) {
        super(context, HORIZONTAL, false);
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        updateView();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrollHorizontallyBy = super.scrollHorizontallyBy(dx, recycler, state);
        updateView();
        return scrollHorizontallyBy;
    }

    private void updateView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            updateScale(view);
            updateOffset(view);
            updateWidth(view);
        }
    }

    private void updateWidth(View view) {
        int parentWidth = getWidth();
        if (mIsHalfMode) {
            parentWidth /= 2;
        }
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int newChildWidth = parentWidth;
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) lp;
            int marginSize = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            newChildWidth = newChildWidth - marginSize;
        }

        lp.width = newChildWidth;
        view.setLayoutParams(lp);
    }

    private void updateOffset(View view) {
        if (mIsHalfMode) {
            view.setTranslationX(0);
        } else {
            if (view.getLeft() < 0) {
                int shift = Math.abs(view.getLeft()) / 2;
                view.setTranslationX(shift);
            }

            if (getWidth() < view.getRight()) {
                int shift = (getWidth() - view.getRight()) / 2;
                view.setTranslationX(shift);
            }
        }
    }

    private void updateScale(View view) {
        float scaleFactor = mIsHalfMode ? 1.f : getScale(view);
        view.setScaleY(Math.max(scaleFactor, MIN_SCALE_HEIGHT));
        view.setScaleX(scaleFactor);
    }

    private float getScale(View view) {
        int viewLeft = view.getLeft();
        int viewRight = view.getRight();
        int parentCenter = getWidth() / 2;

        float width = view.getWidth();
        float mScaledDownItemWidth = width * SCALE_FACTOR;
        float scaleFactor = SCALE_FACTOR;

        if (parentCenter >= viewLeft && parentCenter <= viewRight) {
            int viewCenter = (viewLeft + viewRight) / 2;
            int distanceBetweenCenters = Math.abs(parentCenter - viewCenter);
            float halfOfViewWidth = view.getWidth() / 2f;
            float shiftedFromCenterOn = Math.abs(distanceBetweenCenters / halfOfViewWidth - 1);

            scaleFactor = ((width - mScaledDownItemWidth) * shiftedFromCenterOn + mScaledDownItemWidth)
                    / width;
        }
        return scaleFactor;
    }

    public void setIsHalf(boolean isHalf) {
        mIsHalfMode = isHalf;
        requestLayout();
    }
}