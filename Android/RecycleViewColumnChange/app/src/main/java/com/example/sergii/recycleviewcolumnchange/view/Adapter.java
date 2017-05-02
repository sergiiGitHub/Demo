package com.example.sergii.recycleviewcolumnchange.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergii.recycleviewcolumnchange.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergii on 02.05.17.
 */

public class Adapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Model> models = new LinkedList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setModel(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void addItem(Model aModel){
        models.add(aModel);
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }

    public void setModel(Model model) {
        textView.setText(model.getName());
    }
}

