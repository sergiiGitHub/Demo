package com.example.smuzychuk.todocontentprovider;

import android.net.Uri;

public class TodoContract {

    public static final String NAME = TodoContract.class.getSimpleName().toLowerCase();
    public static final String _ID = "_id";
    public static final String TITLE = "text";
    public static final String DETAIL = "detail";
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".db";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NAME +
                    " ( " +
                    _ID + " integer primary key autoincrement, " +
                    TITLE + " text, " +
                    DETAIL + " text " +
                    " ); ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAME;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NAME);
    //public static final Uri CONTENT_URI_TEMP = Uri.parse("content://" + AUTHORITY + "/" + NAME + "/todo" );
    public static String[] Columns = new String[]{_ID, TITLE, DETAIL};
}
