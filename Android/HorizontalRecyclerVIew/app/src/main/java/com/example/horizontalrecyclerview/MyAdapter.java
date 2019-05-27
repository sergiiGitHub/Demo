package com.example.horizontalrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    private List<MyData> datas;

    public MyAdapter() {

        int size = 10;
        datas = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            MyData data = new MyData();
            data.text = "" + i;
            datas.add(data);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyData myData = datas.get(position);
        holder.setText(myData.text);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
