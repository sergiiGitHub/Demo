package com.example.sergii.appitemview.model;

import android.databinding.Bindable;
import com.example.sergii.appitemview.BR;

/**
 * Created by sergii on 18.12.16.
 */
public class AppItem extends BaseItem {
    private boolean isEdit;

    public AppItem(){
        isEdit = true;
    }

    @Bindable
    public boolean isEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
        notifyPropertyChanged(BR.edit );
    }
}
