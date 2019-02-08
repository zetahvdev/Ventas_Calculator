package com.github.zetahvdev.ventascalc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    // Database info
    private static final String DATABASE_NAME = "productDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Product Table Names
    private static final String TABLE_PRODUCT = ProductFields.TABLE_NAME;

    // Product Table Columns
    private static final String COLUMN_ID = ProductFields.FIELD_ID;
    private static final String COLUMN_PRODUCT = ProductFields.FIELD_PRODUCT;
    private static final String COLUMN_PRIZE = ProductFields.FIELD_PRIZE;
    private static final String COLUMN_CANTIDAD = ProductFields.FIELD_CANTIDAD;
    private static final String COLUMN_TYPE  = ProductFields.FIELD_TYPE;

    ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT +
                "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PRODUCT + " TEXT, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_PRIZE + " INTENGER, "
                + COLUMN_CANTIDAD + " INTEGER" +
                ")";
        Log.w("create table code", CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        if (oldversion != newversion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
            onCreate(db);
        }
    }
}
