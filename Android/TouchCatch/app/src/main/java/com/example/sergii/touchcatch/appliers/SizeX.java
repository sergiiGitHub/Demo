package com.example.sergii.touchcatch.appliers;

import android.view.WindowManager;

/**
 * Created by sergii on 30.09.16.
 */
public class SizeX extends BasicApplier {

    public SizeX(int value) {
        super("size_x", value);
    }

    @Override
    public void apply(WindowManager.LayoutParams aParams) {
        aParams.width = getValue();
    }
}
