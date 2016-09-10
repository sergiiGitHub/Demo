package com.example.sergii.nearbyinterest.recycleview_provider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sergii.nearbyinterest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sergii on 06.04.16.
 */
public class ItemModelAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private static final String TAG = ItemModelAdapter.class.getSimpleName();
    private final Context mContext;
    private List<ItemModel> itemModels;

    public ItemModelAdapter( Context aContext, final List<ItemModel> aItemModels ){
        this.mContext = aContext;
        itemModels = aItemModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final ItemModel model = itemModels.get(position);
        holder.setName(model.getName());
        loadBitmap(model.getUrl(), holder.getView());
    }

    private void loadBitmap(String url, ImageView view) {
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.place_holder)
                //.resize(view.getWidth(), view.getHeight())
                .into(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        Log.d(TAG, "onViewRecycled: ");
        super.onViewRecycled(holder);
        cancelLoad(holder.getView());
    }

    private void cancelLoad(ImageView view) {
        Picasso.with(mContext).cancelRequest(view);
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public void appendItem(ItemModel aItem) {
        itemModels.add( aItem );
        notifyItemInserted(itemModels.size() - 1);
    }
}
