package com.example.horizontalrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sergii Muzychuk (sergii.muzychuk@globallogic.com) on 27.05.19.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.title);

    }

    public void setText(String text) {
        textView.setText(text);
    }
}
