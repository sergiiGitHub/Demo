package com.example.sergii.touchcatch.appliers;

import android.view.WindowManager;

/**
 * Created by sergii on 30.09.16.
 */
public class PositionX extends BasicApplier {

    public PositionX(int value) {
        super("position_x", value);
    }

    @Override
    public void apply(WindowManager.LayoutParams aParams) {
        aParams.x = getValue();
    }
}
