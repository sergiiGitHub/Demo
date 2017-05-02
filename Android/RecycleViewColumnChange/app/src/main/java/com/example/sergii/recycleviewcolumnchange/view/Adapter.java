package com.example.sergii.recycleviewcolumnchange.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private static final String TAG = Adapter.class.getSimpleName();
    private List<Model> models = new LinkedList<>();
    private int spanConstant = 20;
    private int currentSpan;
    private int multiplier = 1;
    private GridRecyclerView grid;
    public int totalSpan = 100;

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

    public int calculateRange() {
        int start = ((GridLayoutManager) grid.getLayoutManager()).findFirstVisibleItemPosition();
        int end = ((GridLayoutManager) grid.getLayoutManager()).findLastVisibleItemPosition();
        if (start < 0)
            start = 0;
        if (end < 0)
            end = 0;
        return end - start;
    }

    public int getCurrentSpan() {
        return currentSpan;
    }

    public void setCurrentSpan(int span) {
        Log.d(TAG, "setCurrentSpan: span" + span);
        this.currentSpan = span;

    }

    public void delayedNotify(final int pos, final int range) {
        grid.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeChanged(pos - range > 0 ? pos - range : 0, range * 2 < getItemCount() ? range * 2 : range);
            }
        }, 100);
    }

    public void setGridRecycleView(GridRecyclerView grid) {
        this.grid = grid;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @
                        Override
                public void onClick(View v) {
                    setCurrentSpan(++multiplier);
                    delayedNotify(getAdapterPosition(), calculateRange());
                }
            });
        }

        public void setModel(Model model) {
            textView.setText(model.getName());
        }
    }
}
