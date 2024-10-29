package com.example.hm_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShoppingListDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shoppingList.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LISTS = "Lists";
    public static final String TABLE_TYPE = "Type";
    public static final String TABLE_PRODUCT = "Product";

    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_NAME = "name";
    public static final String COLUMN_LIST_DATE = "date";
    public static final String COLUMN_LIST_DESCRIPTION = "description";

    public static final String COLUMN_TYPE_ID = "_id";
    public static final String COLUMN_TYPE_LABEL = "label";
    public static final String COLUMN_TYPE_RULE = "rule";

    public static final String COLUMN_PRODUCT_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_COUNT = "count";
    public static final String COLUMN_PRODUCT_LIST_ID = "list_id";
    public static final String COLUMN_PRODUCT_CHECKED = "checked";
    public static final String COLUMN_PRODUCT_COUNT_TYPE = "count_type";


    private static final String CREATE_TABLE_LISTS = "CREATE TABLE " + TABLE_LISTS + " ("
            + COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LIST_NAME + " TEXT NOT NULL, "
            + COLUMN_LIST_DATE + " INTEGER NOT NULL, "
            + COLUMN_LIST_DESCRIPTION + " TEXT);";

    private static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TABLE_TYPE + " ("
            + COLUMN_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TYPE_LABEL + " TEXT NOT NULL, "
            + COLUMN_TYPE_RULE + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + " ("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
            + COLUMN_PRODUCT_COUNT + " REAL NOT NULL, "
            + COLUMN_PRODUCT_LIST_ID + " INTEGER NOT NULL, "
            + COLUMN_PRODUCT_CHECKED + " INTEGER NOT NULL, "
            + COLUMN_PRODUCT_COUNT_TYPE + " INTEGER NOT NULL);";

    public ShoppingListDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LISTS);
        db.execSQL(CREATE_TABLE_TYPE);
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }


}

