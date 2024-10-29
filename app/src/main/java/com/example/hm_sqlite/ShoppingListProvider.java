package com.example.hm_sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShoppingListProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.shoppinglist.provider";
    private static final String BASE_PATH_LISTS = "lists";
    private static final String BASE_PATH_TYPE = "type";
    private static final String BASE_PATH_PRODUCT = "product";

    public static final Uri CONTENT_URI_LISTS = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_LISTS);
    public static final Uri CONTENT_URI_TYPE = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_TYPE);
    public static final Uri CONTENT_URI_PRODUCT = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_PRODUCT);

    private static final int LISTS = 1;
    private static final int TYPE = 2;
    private static final int PRODUCT = 3;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH_LISTS, LISTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH_TYPE, TYPE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH_PRODUCT, PRODUCT);
    }

    private int matchUri(Uri uri) {
        return uriMatcher.match(uri);
    }

    private ShoppingListDatabaseHelper database;

    @Override
    public boolean onCreate() {
        database = new ShoppingListDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = database.getWritableDatabase();
        long id;
        Uri resultUri = null;

        switch (matchUri(uri)) {
            case LISTS:
                id = db.insert(ShoppingListDatabaseHelper.TABLE_LISTS, null, values);
                resultUri = ContentUris.withAppendedId(CONTENT_URI_LISTS, id);
                break;
            case TYPE:
                id = db.insert(ShoppingListDatabaseHelper.TABLE_TYPE, null, values);
                resultUri = ContentUris.withAppendedId(CONTENT_URI_TYPE, id);
                break;
            case PRODUCT:
                id = db.insert(ShoppingListDatabaseHelper.TABLE_PRODUCT, null, values);
                resultUri = ContentUris.withAppendedId(CONTENT_URI_PRODUCT, id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return resultUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor;

        switch (matchUri(uri)) {
            case LISTS:
                cursor = db.query(ShoppingListDatabaseHelper.TABLE_LISTS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TYPE:
                cursor = db.query(ShoppingListDatabaseHelper.TABLE_TYPE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT:
                cursor = db.query(ShoppingListDatabaseHelper.TABLE_PRODUCT, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "";
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = database.getWritableDatabase();
        int rowsUpdated;

        switch (matchUri(uri)) {
            case LISTS:
                rowsUpdated = db.update(ShoppingListDatabaseHelper.TABLE_LISTS, values, selection, selectionArgs);
                break;
            case TYPE:
                rowsUpdated = db.update(ShoppingListDatabaseHelper.TABLE_TYPE, values, selection, selectionArgs);
                break;
            case PRODUCT:
                rowsUpdated = db.update(ShoppingListDatabaseHelper.TABLE_PRODUCT, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = database.getWritableDatabase();
        int rowsDeleted;

        switch (matchUri(uri)) {
            case LISTS:
                rowsDeleted = db.delete(ShoppingListDatabaseHelper.TABLE_LISTS, selection, selectionArgs);
                break;
            case TYPE:
                rowsDeleted = db.delete(ShoppingListDatabaseHelper.TABLE_TYPE, selection, selectionArgs);
                break;
            case PRODUCT:
                rowsDeleted = db.delete(ShoppingListDatabaseHelper.TABLE_PRODUCT, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }


}
