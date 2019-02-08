package com.github.zetahvdev.ventascalc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProductDBManager {

    private ProductDBManager() {}

    public static ArrayList<Product> LoadAll(SQLiteDatabase db) {
        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = db.query(
                ProductFields.TABLE_NAME,
                null, null,
                null, null, null, null);

        while(cursor.moveToNext()) {
            String product = cursor.getString(cursor.getColumnIndexOrThrow(ProductFields.FIELD_PRODUCT));
            int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(ProductFields.FIELD_CANTIDAD));
            int prize = cursor.getInt(cursor.getColumnIndexOrThrow(ProductFields.FIELD_PRIZE));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(ProductFields.FIELD_TYPE));
            Product prod = new Product(product, type, prize, cantidad);
            products.add(prod);
        }
        cursor.close();
        return products;
    }

    public static void SaveAll(ArrayList<Product> products, SQLiteDatabase db) {
        for (Product prod : products) {
            ContentValues values = new ContentValues();
            values.put(ProductFields.FIELD_PRODUCT, prod.getProduct());
            values.put(ProductFields.FIELD_TYPE, prod.getType());
            values.put(ProductFields.FIELD_PRIZE, prod.getPrize());
            values.put(ProductFields.FIELD_CANTIDAD, prod.getAmount());

            db.insert(ProductFields.TABLE_NAME, null, values);
        }
    }

}
