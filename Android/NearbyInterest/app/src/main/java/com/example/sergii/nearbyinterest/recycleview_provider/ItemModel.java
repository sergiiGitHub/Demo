package com.example.sergii.nearbyinterest.recycleview_provider;

/**
 * Created by sergii on 06.04.16.
 */
public class ItemModel {

    private String name;
    //picture
    //type

    public ItemModel( String aName ){
        setName(aName);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
