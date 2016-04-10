package com.example.sergii.nearbyinterest.recycleview_provider;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergii.nearbyinterest.R;

/**
 * Created by sergii on 06.04.16.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private ImageView imageView;

    public ViewHolder(View view) {
        super(view);
        name = (TextView) view.findViewById(R.id.id_name);
        imageView = (ImageView) view.findViewById(R.id.id_image);
    }

    public void setName(final String aName) {
        name.setText(aName);
    }

    public void setBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
