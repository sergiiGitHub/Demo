package com.example.myapprecycler.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sergii Muzychuk (sergii.muzychuk@globallogic.com) on 28.05.19.
 */

public class HalfZoomLayout extends LinearLayoutManager {

    private static final float SCALE_HALF = 1.f;
    private static final float SCALE_FACTOR = 0.5f;
    private static final float MIN_SCALE_HEIGHT = 0.75f;
    private static final int HALF_TRANSLATION_X = 0;

    private boolean mIsHalfMode = false;

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
        final int translationX = mIsHalfMode ? getHalfTranslation() : getNormalTranslation(view);
        view.setTranslationX(translationX);
    }

    private int getHalfTranslation() {
        return HALF_TRANSLATION_X;
    }

    private int getNormalTranslation(View view) {
        int shift = 0;
        if (view.getLeft() < 0) {
            shift = Math.abs(view.getLeft()) / 2;
        } else if (getWidth() < view.getRight()) {
            shift = (getWidth() - view.getRight()) / 2;
        }
        return shift;
    }

    private void updateScale(View view) {
        float scaleFactor = mIsHalfMode ? getScaleHalf() : getScaleNormal(view);
        view.setScaleY(Math.max(scaleFactor, MIN_SCALE_HEIGHT));
        view.setScaleX(scaleFactor);
    }

    private float getScaleHalf() {
        return SCALE_HALF;
    }

    private float getScaleNormal(View view) {
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

            scaleFactor = ((width - mScaledDownItemWidth) * shiftedFromCenterOn +
                    mScaledDownItemWidth) / width;
        }
        return scaleFactor;
    }

    public void setIsHalf(boolean isHalf) {
        mIsHalfMode = isHalf;
        requestLayout();
    }
}
