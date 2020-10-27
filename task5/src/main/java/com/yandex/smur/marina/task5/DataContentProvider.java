package com.yandex.smur.marina.task5;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.yandex.smur.marina.task5";
    private static final int URI_CONTACT_CODE = 1;
//    private static final String CONTACT_PATH = "ContactPlus";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "data/data", URI_CONTACT_CODE);
    }
//
//    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
//            + AUTHORITY + "/" + CONTACT_PATH);

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
//    public static final String[] ALL_COLUMNS =
//            {"KEY_ID", "KEY_NAME", "KEY_INFO", "KEY_IMAGE"};

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        sqLiteDatabase  = dbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == URI_CONTACT_CODE) {
            cursor = sqLiteDatabase.query("ContactPlus", projection, selection, null,
                    null, null, sortOrder);
        }
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return "com.yandex.smur.marina.task5/data";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
