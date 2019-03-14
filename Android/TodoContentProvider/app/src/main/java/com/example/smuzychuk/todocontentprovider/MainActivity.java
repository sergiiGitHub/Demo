package com.example.smuzychuk.todocontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.smuzychuk.todocontentprovider.adapter.CustomCursorRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements CustomCursorRecyclerViewAdapter.AdapterClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateButton();
        populateRV();

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    private void populateRV() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        CustomCursorRecyclerViewAdapter mAdapter = new CustomCursorRecyclerViewAdapter(this, null);
        mAdapter.setListener(this);

        mRecyclerView = findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void populateButton() {
        Button button = findViewById(R.id.insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertNewRecord();
            }
        });
    }

    Uri insertNewRecord(){
        ContentValues values = new ContentValues();

        values.put(TodoContract.TITLE, ("title " + 0));
        values.put(TodoContract.DETAIL, ("detail " + 0));

        return getContentResolver().
                insert(TodoContract.CONTENT_URI, values);
    }

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition(-1);
        while (data.moveToNext()) {
            mx.addRow(new Object[]{
                    data.getString(data.getColumnIndex(TodoContract._ID)),
                    data.getString(data.getColumnIndex(TodoContract.TITLE)),
                    data.getString(data.getColumnIndex(TodoContract.DETAIL))
            });
        }
    }

    @Override
    public void onClick(int position) {
        delete(position);
    }

    private void delete(int position) {
        long id = mRecyclerView.getAdapter().getItemId(position);
        getContentResolver().delete(TodoContract.CONTENT_URI, TodoContract._ID + "=?",
                new String[] {Long.toString(id)});
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(this, TodoContract.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        //fill with additional result
        MatrixCursor mx = new MatrixCursor(TodoContract.Columns);
        fillMx(data, mx);

        ((CustomCursorRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor(mx);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
