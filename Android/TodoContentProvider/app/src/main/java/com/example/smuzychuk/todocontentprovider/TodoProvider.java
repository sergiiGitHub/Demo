package com.example.smuzychuk.todocontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class TodoProvider extends ContentProvider {

    private static final String TAG = TodoProvider.class.getSimpleName();

    private TodoDbHelper mOpenHelper;

    public static final int CODE_TODO = 100;
    public static final int CODE_TODO_WITH_ID = 101;

    /**
     * In onCreate, we initialize our content provider on startup. This method is called for all
     * registered content providers on the application main thread at application launch time.
     * It must not perform lengthy operations, or application startup will be delayed.
     *
     * @return true if the provider was successfully loaded, false otherwise
     */
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    /**
     * Creates the UriMatcher that will match each URI to the CODE_TODO and
     * CODE_TODO_WITH_ID constants defined above.
     *
     * UriMatcher does all the hard work for you. You just have to tell it which code to match
     * with which URI, and it does the rest automatically.
     *
     * @return A UriMatcher that correctly matches the constants for CODE_TODO and CODE_TODO_WITH_ID
     */
    public static UriMatcher buildUriMatcher() {

        /*
         * All paths added to the UriMatcher have a corresponding code to return when a match is
         * found. The code passed into the constructor of UriMatcher here represents the code to
         * return for the root URI. It's common to use NO_MATCH as the code for this case.
         */
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TodoContract.AUTHORITY;

        /*
         * For each type of URI you want to add, create a corresponding code. Preferably, these are
         * constant fields in your class so that you can use them throughout the class and you no
         * they aren't going to change. In Todo, we use CODE_TODO or CODE_TODO_WITH_ID.
         */

        /* This URI is content://com.example.todo/todo/ */
        matcher.addURI(authority, TodoContract.NAME, CODE_TODO);

        /*
         * This URI would look something like content://com.example.todo/todo/1
         * The "/#" signifies to the UriMatcher that if TABLE_NAME is followed by ANY number,
         * that it should return the CODE_TODO_WITH_ID code
         */
        matcher.addURI(authority, TodoContract.NAME + "/#", CODE_TODO_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new TodoDbHelper(getContext());
        return mOpenHelper != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        /*
         * Here's the switch statement that, given a URI, will determine what kind of request is
         * being made and query the database accordingly.
         */
        switch (sUriMatcher.match(uri)) {

            /*
             * When sUriMatcher's match method is called with a URI that looks something like this
             *
             *      content://com.example.todo/todo/2
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return the todo for a particular id. The id in this code is encoded in
             * int and is at the very end of the URI (2) and can be accessed
             * programmatically using Uri's getLastPathSegment method.
             *
             * In this case, we want to return a cursor that contains one row of todo data for
             * a particular date.
             */
            case CODE_TODO_WITH_ID: {

                /*
                 * In order to determine the id associated with this URI, we look at the last
                 * path segment.
                 */
                String _ID = uri.getLastPathSegment();

                /*
                 * The query method accepts a string array of arguments, as there may be more
                 * than one "?" in the selection statement. Even though in our case, we only have
                 * one "?", we have to create a string array that only contains one element
                 * because this method signature accepts a string array.
                 */
                String[] selectionArguments = new String[]{_ID};

                cursor = mOpenHelper.getReadableDatabase().query(
                        /* Table we are going to query */
                        TodoContract.NAME,
                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,
                        /*
                         * The URI that matches CODE_TODO_WITH_ID contains a id at the end
                         * of it. We extract that id and use it with these next two lines to
                         * specify the row of todo we want returned in the cursor. We use a
                         * question mark here and then designate selectionArguments as the next
                         * argument for performance reasons. Whatever Strings are contained
                         * within the selectionArguments array will be inserted into the
                         * selection statement by SQLite under the hood.
                         */
                        TodoContract._ID + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder);

                break;
            }

            /*
             * When sUriMatcher's match method is called with a URI that looks EXACTLY like this
             *
             *      content://com.example.todo/todo
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return all of the records in our todo table.
             *
             * In this case, we want to return a cursor that contains every record
             * in our todo table.
             */
            case CODE_TODO: {
                cursor = mOpenHelper.getReadableDatabase().query(
                        TodoContract.NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    //todo inset 5 insert 1

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id = -1;
        switch (sUriMatcher.match(uri)) {
            case CODE_TODO:
                Log.d(TAG, "insert: ");
                 _id = db.insert(TodoContract.NAME, null, values);
                 break;
            case CODE_TODO_WITH_ID:
                _id = db.insert(TodoContract.NAME, null, values);
                break;
            default:
                return null;
        }

        /* if _id is equal to -1 insertion failed */
        if (_id != -1) {
            /*
             * This will help to broadcast that database has been changed,
             * and will inform entities to perform automatic update.
             */
            Uri retUri = ContentUris.withAppendedId(uri, _id);
            getContext().getContentResolver().notifyChange(retUri, null);
            return retUri;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int delCount = -1;
        switch (sUriMatcher.match(uri)) {
            case CODE_TODO:
            case CODE_TODO_WITH_ID:
                delCount = db.delete(TodoContract.NAME, selection, selectionArgs);
                break;
            default:
                return delCount;
        }
        if (delCount != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int delCount = -1;
        switch (sUriMatcher.match(uri)) {
            case CODE_TODO:
            case CODE_TODO_WITH_ID:
                delCount = db.update(TodoContract.NAME, values, selection, selectionArgs);
                break;
            default:
                return delCount;
        }
        if (delCount != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delCount;
    }
}
