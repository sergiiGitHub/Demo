package com.example.sergii.appitemview;

/**
 * Created by sergii on 14.12.16.
 *
 * For read
 * https://developer.android.com/topic/libraries/data-binding/index.html#binding_data
 * https://stfalcon.com/ru/blog/post/faster-android-apps-with-databinding
 */
public class BasicItem {

    private String mTitle = "default title";
    private String mIconPath;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmIconPath() {
        return mIconPath;
    }

    public void setmIconPath(String mIconPath) {
        this.mIconPath = mIconPath;
    }
}
