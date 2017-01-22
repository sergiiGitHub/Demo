package com.example.sergii.appitemview;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.sergii.appitemview.databinding.AppItemBinding;
import com.example.sergii.appitemview.model.AppItem;
import com.example.sergii.appitemview.model.BaseItem;

import java.util.List;

/**
 * Created by sergii on 18.12.16.
 */
public class AppAdapter < M extends BaseItem> extends BaseAdapter {

    private static final String TAG = AppAdapter.class.getSimpleName();
    private final List<M> models;

    public AppAdapter( List<M> models ){
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AppItemBinding binding;
        if ( convertView == null ){
            Log.d(TAG, "getView: inflate");
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.app_item, parent, false);
        } else {
            Log.d(TAG, "getView: bind");
            binding = DataBindingUtil.bind(convertView);
        }

        M item = models.get(position);
        binding.setAppItem((AppItem) item);
        return binding.getRoot();
    }
}
