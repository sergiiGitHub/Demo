package com.example.smuzychuk.todocontentprovider.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smuzychuk.todocontentprovider.R;
import com.example.smuzychuk.todocontentprovider.view.CustomViewHolder;

/**
 * Created by deadfish on 2016-01-28.
 */
public class CustomCursorRecyclerViewAdapter extends CursorRecyclerViewAdapter {

    public AdapterClickListener listener;

    public CustomCursorRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        final CustomViewHolder customViewHolder = new CustomViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + customViewHolder.getAdapterPosition());
                if (listener != null) {
                    listener.onClick(customViewHolder.getAdapterPosition());
                }
            }
        });
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        CustomViewHolder holder = (CustomViewHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setData(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void setListener(AdapterClickListener listener) {
        this.listener = listener;
    }

    public interface AdapterClickListener {
        void onClick(int position);
    }
}