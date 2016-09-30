package com.example.sergii.touchcatch.appliers;

import android.view.WindowManager;

/**
 * Created by sergii on 30.09.16.
 */
public class SizeY extends BasicApplier {

    public SizeY(int value) {
        super("size_y", value);
    }

    @Override
    public void apply(WindowManager.LayoutParams aParams) {
        aParams.height = getValue();
    }
}
