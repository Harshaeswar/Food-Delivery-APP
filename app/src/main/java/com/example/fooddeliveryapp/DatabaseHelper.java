package com.example.fooddeliveryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FoodDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "FoodItems";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PRICE + " REAL," +
                COLUMN_IMAGE + " TEXT)";
        db.execSQL(CREATE_TABLE);

        insertFood(db, "Cheese Margherita Pizza", 250.0, "pizza");
        insertFood(db, "Classic Burger", 150.0, "burger");
        insertFood(db, "Chicken Biryani", 200.0, "biryani");
        insertFood(db, "Veg Fried Rice", 120.0, "fried_rice");
        insertFood(db, "Masala Soda", 50.0, "masala_soda");
        insertFood(db, "Mango Lassi", 80.0, "lassi");
        insertFood(db, "Milkshake", 100.0, "milkshake");
        insertFood(db, "Chicken Starters", 180.0, "chicken_starters");
    }

    private void insertFood(SQLiteDatabase db, String name, double price, String image) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_IMAGE, image);
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<FoodItem> getAllFoodItems() {
        ArrayList<FoodItem> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                double price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
                String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
                foodList.add(new FoodItem(id, name, price, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}