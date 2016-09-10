package com.example.sergii.nearbyinterest.recycleview_provider;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergii.nearbyinterest.R;

/**
 * Created by sergii on 06.04.16.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = ViewHolder.class.getSimpleName();;
    private TextView mName;
    private ImageView mImageView;

    public ViewHolder(View view) {
        super(view);
        mName = (TextView) view.findViewById(R.id.id_name);
        mImageView = (ImageView) view.findViewById(R.id.id_image);
        Log.d(TAG, "ViewHolder: w: " + mImageView.getWidth() + "; h: " + mImageView.getHeight());
    }

    public void setName(final String aName) {
        mName.setText(aName);
    }

    public ImageView getView() {
        return mImageView;
    }
}
