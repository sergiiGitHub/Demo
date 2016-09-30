package com.example.sergii.touchcatch.appliers;

import android.view.WindowManager;

/**
 * Created by sergii on 30.09.16.
 */
public class PositionY extends BasicApplier {

    public PositionY(int value) {
        super("position_y", value);
    }

    @Override
    public void apply(WindowManager.LayoutParams aParams) {
        aParams.y = getValue();
    }
}
