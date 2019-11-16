package com.example.myapprecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class MyAdapterSimple extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = MyAdapterSimple.class.getSimpleName();

    private List<MyData> mDatas;

    public MyAdapterSimple(ArrayList<MyData> array) {
        mDatas = array;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyData myData = mDatas.get(position);
        holder.getTextView().setText(myData.getText());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void remove(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    public void add(int pos, MyData data) {
        mDatas.add(pos, data);
        notifyItemInserted(pos);
    }
}
