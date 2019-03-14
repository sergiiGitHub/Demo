package com.example.smuzychuk.todocontentprovider.view;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.smuzychuk.todocontentprovider.R;
import com.example.smuzychuk.todocontentprovider.TodoContract;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView description;

    public CustomViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
    }

    public void setData(Cursor c) {
        title.setText(c.getString(c.getColumnIndex(TodoContract.TITLE)));
        description.setText(c.getString(c.getColumnIndex(TodoContract.DETAIL)));
    }
}
