package com.example.smuzychuk.todocontentprovider;

import android.content.ContentUris;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.smuzychuk.todocontentprovider.adapter.CustomCursorRecyclerViewAdapter;
import com.example.smuzychuk.todocontentprovider.dialog.BaseDialog;
import com.example.smuzychuk.todocontentprovider.dialog.EDitDialog;

public class MainActivity extends AppCompatActivity implements CustomCursorRecyclerViewAdapter.AdapterClickListener,
        LoaderManager.LoaderCallbacks<Cursor>,BaseDialog.OnApplyListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private boolean isMultiple = false;
    private BaseDialog baseDialog;

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
                if (isMultiple) {
                    insertMultiple();
                } else {
                    insertNewRecord();
                }
                isMultiple = !isMultiple;
            }
        });
    }

    Uri insertNewRecord(){
        ContentValues values = new ContentValues();

        values.put(TodoContract.TITLE, ("title " + 0));
        values.put(TodoContract.DETAIL, ("detail " + 0));

        return getContentResolver().insert(TodoContract.CONTENT_URI, values);
    }

    private void insertMultiple() {
        int size = 3;
        ContentValues[] cvArray = new ContentValues[size];
        for (int i = 0; i < cvArray.length; i++) {
            ContentValues cv = new ContentValues();
            cv.put(TodoContract.TITLE, ("title " + i));
            cv.put(TodoContract.DETAIL, ("detail " + i));
            cvArray[i] = cv;
        }

        getContentResolver().bulkInsert(TodoContract.CONTENT_URI, cvArray);
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
    public void onClick(final int position, final View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenu().add(1, R.id.popup_delete, 1, "delete");
        popupMenu.getMenu().add(1, R.id.popup_edit, 2, "edit");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.popup_delete:
                                delete(position);
                                return true;
                            case R.id.popup_edit:
                                showEditDialog(position, view);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        popupMenu.show();
    }

    private void showEditDialog(int position, View view) {
        if (baseDialog == null) {
            baseDialog = new EDitDialog();
            baseDialog.setOnApplyListener(this);
        }
        TextView titleTV = view.findViewById(R.id.title);
        TextView detailTV = view.findViewById(R.id.detail);
        Bundle bundle = new Bundle();
        bundle.putString(EDitDialog.KEY_TITLE, titleTV.getText().toString());
        bundle.putString(EDitDialog.KEY_DETAIL, detailTV.getText().toString());
        bundle.putInt(EDitDialog.KEY_POSITION, position);
        baseDialog.show(this, bundle);
    }

    @Override
    public void onDialogResult(Bundle bundle) {
        String strTitle = bundle.getString(EDitDialog.KEY_TITLE);
        String strDetail = bundle.getString(EDitDialog.KEY_DETAIL);
        int position = bundle.getInt(EDitDialog.KEY_DETAIL);
        applyEdit(position, strTitle, strDetail);
    }

    private void applyEdit(int position, String title, String details) {
        Log.d(TAG, "applyEdit: "  + title + "; " +  details);
        long id = mRecyclerView.getAdapter().getItemId(position);
        Uri contentUri = ContentUris.withAppendedId(TodoContract.CONTENT_URI, id);
        ContentValues values = new ContentValues();

        values.put(TodoContract.TITLE, title);
        values.put(TodoContract.DETAIL, details);

        getContentResolver().update(contentUri, values, TodoContract._ID + "=?", new String[] {Long.toString(id)});
    }

    private void delete(int position) {
        long id = mRecyclerView.getAdapter().getItemId(position);
        Uri contentUri = ContentUris.withAppendedId(TodoContract.CONTENT_URI, id);
        getContentResolver().delete(contentUri, TodoContract._ID + "=?",
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
