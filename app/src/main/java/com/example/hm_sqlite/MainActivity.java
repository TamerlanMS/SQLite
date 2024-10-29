package com.example.hm_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ShoppingListDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new ShoppingListDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        insertInitialData(db);

        boolean dbExists = checkDatabaseExists(this, "shoppingList.db");
        if (dbExists) {
            Log.d("DATABASE", "Данные добавлены");
        } else {
            Log.d("DATABASE", "Не добавлены");
        }
    }

    public boolean checkDatabaseExists(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues listValues = new ContentValues();
        listValues.put("name", "List 1");
        listValues.put("date", 1449462120);
        listValues.put("description", "Lorem ipsum dolor sit amet");
        db.insert(ShoppingListDatabaseHelper.TABLE_LISTS, null, listValues);

        listValues.clear();
        listValues.put("name", "List 2");
        listValues.put("date", 1449472233);
        listValues.put("description", (String) null);
        db.insert(ShoppingListDatabaseHelper.TABLE_LISTS, null, listValues);

        listValues.clear();
        listValues.put("name", "List 3");
        listValues.put("date", 1449483585);
        listValues.put("description", (String) null);
        db.insert(ShoppingListDatabaseHelper.TABLE_LISTS, null, listValues);

        ContentValues typeValues = new ContentValues();
        typeValues.put("label", "шт");
        typeValues.put("rule", "int");
        db.insert(ShoppingListDatabaseHelper.TABLE_TYPE, null, typeValues);

        typeValues.clear();
        typeValues.put("label", "кг");
        typeValues.put("rule", "float");
        db.insert(ShoppingListDatabaseHelper.TABLE_TYPE, null, typeValues);

        typeValues.clear();
        typeValues.put("label", "л");
        typeValues.put("rule", "float");
        db.insert(ShoppingListDatabaseHelper.TABLE_TYPE, null, typeValues);

        ContentValues productValues = new ContentValues();
        productValues.put("name", "Product 1");
        productValues.put("count", 0.5);
        productValues.put("list_id", 1);
        productValues.put("checked", 1);
        productValues.put("count_type", 2);
        db.insert(ShoppingListDatabaseHelper.TABLE_PRODUCT, null, productValues);

        productValues.clear();
        productValues.put("name", "Product 2");
        productValues.put("count", 1);
        productValues.put("list_id", 1);
        productValues.put("checked", 0);
        productValues.put("count_type", 1);
        db.insert(ShoppingListDatabaseHelper.TABLE_PRODUCT, null, productValues);

        productValues.clear();
        productValues.put("name", "Product 3");
        productValues.put("count", 2);
        productValues.put("list_id", 2);
        productValues.put("checked", 0);
        productValues.put("count_type", 1);
        db.insert(ShoppingListDatabaseHelper.TABLE_PRODUCT, null, productValues);
    }
}