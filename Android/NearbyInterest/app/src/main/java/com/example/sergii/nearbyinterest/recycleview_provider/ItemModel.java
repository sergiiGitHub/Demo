package com.example.sergii.nearbyinterest.recycleview_provider;


/**
 * Created by sergii on 06.04.16.
 */
public class ItemModel {

    private static final String IDS_TAB = "\t";
    private static final String TAG = ItemModel.class.getSimpleName();
    private static final StringBuilder SB_REQUEST_PHOTO = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?")
            .append("maxwidth=200")// TODO: 10.04.16 change
            .append("&key=AIzaSyAm5EsTdiPxwju2oYF5PMT7JJdM82gBcIc")
            .append("&photoreference=");

    private String name;
    private String type;
    private String mUrl;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setReference(String aReference) {
        this.mUrl = new StringBuilder(SB_REQUEST_PHOTO.toString())
                .append(aReference).toString();
    }

    public String getUrl(){
        return mUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name)
                .append(IDS_TAB).append(type);
        return sb.toString();
    }
}
