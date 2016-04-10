package com.example.sergii.nearbyinterest.recycleview_provider;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergii.nearbyinterest.R;

import java.util.List;

/**
 * Created by sergii on 06.04.16.
 */
public class ItemModelAdapter extends RecyclerView.Adapter<ViewHolder>
implements ItemModel.ItemModelListener
{
    private List<ItemModel> itemModels;

    public ItemModelAdapter( final List<ItemModel> aItemModels ){
        itemModels = aItemModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemModel movie = itemModels.get(position);
        holder.setName(movie.getName());
        holder.setBitmap(movie.getBitmap());
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public void appndItem(ItemModel aItem) {
        itemModels.add( aItem );
        aItem.setItemModelListener(this);
        notifyItemInserted(itemModels.size() - 1);
    }

    @Override
    public void onItemUpdate(int aId ) {
        notifyItemChanged(aId);
    }
}