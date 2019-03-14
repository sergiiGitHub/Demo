package com.example.smuzychuk.todocontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class TodoDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "CustomSqliteOpenHelper";

    public TodoDbHelper(Context context) {
        super(context, "db.db", null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TodoContract.DROP_TABLE);
        onCreate(db);
    }
}
