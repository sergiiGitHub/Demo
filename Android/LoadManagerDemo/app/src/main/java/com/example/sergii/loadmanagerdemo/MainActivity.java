package com.example.sergii.loadmanagerdemo;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity
implements LoaderManager.LoaderCallbacks<Cursor>{

    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, null,
                MyContactsProvider.ALL_VIEW,
                new int[]{android.R.id.text1, android.R.id.text2}, 0);

        ListView lvContact = (ListView) findViewById(R.id.lvContact);
        lvContact.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This is called when a new Loader needs to be created.  This
        // sample only has one Loader, so we don't care about the ID.
        // First, pick the base URI to use depending on whether we are
        // currently filtering.

        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        String select = "(" + MyContactsProvider.CONTACT_NAME+ " NOTNULL)";
        return new CursorLoader(this, MyContactsProvider.CONTACT_CONTENT_URI,
                MyContactsProvider.PROJECTION, select, null,
                MyContactsProvider.CONTACT_NAME+ " COLLATE LOCALIZED ASC");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);

        // The list should now be shown.
//        if (isResumed()) {
//            setListShown(true);
//        } else {
//            setListShownNoAnimation(true);
//        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
