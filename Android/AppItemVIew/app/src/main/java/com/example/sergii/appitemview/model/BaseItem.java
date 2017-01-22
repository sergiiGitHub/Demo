package com.example.sergii.appitemview.model;

import android.databinding.BaseObservable;

/**
 * Created by sergii on 14.12.16.
 *
 * For read
 * https://developer.android.com/topic/libraries/data-binding/index.html#binding_data
 * https://stfalcon.com/ru/blog/post/faster-android-apps-with-databinding
 */

public class BaseItem extends BaseObservable {

    private String title = "default title";
    private String mIconPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getmIconPath() {
        return mIconPath;
    }

    public void setmIconPath(String mIconPath) {
        this.mIconPath = mIconPath;
    }
}
