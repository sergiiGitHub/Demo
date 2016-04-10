package com.example.sergii.nearbyinterest.recycleview_provider;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.sergii.nearbyinterest.MainActivity;

/**
 * Created by sergii on 06.04.16.
 */
public class ItemModel implements MainActivity.BitmapListener {

    private static final String IDS_TAB = "\t";
    private static final String TAG = ItemModel.class.getSimpleName();

    private String name;
    private String type;
    private Bitmap bitmap;
    private ItemModelListener itemModelListener;
    private int id;

    public ItemModel(int aId, String aName, String aType){
        setId(aId);
        setName(aName);
        setType(aType);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public void setBitmap(Bitmap aBitmap) {
        Log.d(TAG, "setBitmap: " + aBitmap + "; name: " + name );
        bitmap = aBitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name)
        .append(IDS_TAB).append(type)
        .append(IDS_TAB).append(bitmap);
        return sb.toString();
    }

    @Override
    public void onLoadFinish(Bitmap result) {
        Log.d(TAG, "onLoadFinish: " + result);
        if ( result != null && getItemModelListener() != null  ) {
            setBitmap(result);
            getItemModelListener().onItemUpdate(id);
        }
    }

    public ItemModelListener getItemModelListener() {
        return itemModelListener;
    }

    public void setItemModelListener(ItemModelListener itemModelListener) {
        this.itemModelListener = itemModelListener;
    }

    public void setId(int id) {
        this.id = id;
    }

    public interface ItemModelListener {
        void onItemUpdate( int aId );
    }
}
