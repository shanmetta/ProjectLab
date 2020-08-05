package com.example.projectmadlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class databaseHelper2 extends SQLiteOpenHelper {

    private SQLiteDatabase mDatabase;
    public static final String TAG = "DatabaseHelper2";
    public static final String TABLE_NAME = "DETAIL_TABLE";
    public static final String COLUMN1 = "ID";
    public static final String COLUMN2 = "name";
    public static final String COLUMN3 = "address";
    public static final String COLUMN4 = "checkin";
    public static final String COLUMN5 = "checkout";
    public static final String COLUMN6 = "totalPrice";

    public databaseHelper2(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, checkin TEXT, checkout TEXT, totalPrice TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void  addData(String name, String address, String checkin, String checkout, String totalPice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2, name);
        contentValues.put(COLUMN3, address);
        contentValues.put(COLUMN4, checkin);
        contentValues.put(COLUMN5, checkout);
        contentValues.put(COLUMN6, totalPice);
        long  res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }


public ArrayList<HashMap<String, String>> GetData() {
    SQLiteDatabase db = this.getWritableDatabase();
    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    String query = "SELECT * FROM " + TABLE_NAME;
    Cursor cursor = db.rawQuery(query,null);
    while (cursor.moveToNext()){
        HashMap<String, String> data = new HashMap<>();
        data.put("ID", cursor.getString(cursor.getColumnIndex(COLUMN1)));
        data.put("name", cursor.getString(cursor.getColumnIndex(COLUMN2)));
        data.put("address", cursor.getString(cursor.getColumnIndex(COLUMN3)));
        data.put("checkindate", cursor.getString(cursor.getColumnIndex(COLUMN4)));
        data.put("checkoutdate", cursor.getString(cursor.getColumnIndex(COLUMN5)));
        data.put("totalprice", cursor.getString(cursor.getColumnIndex(COLUMN6)));
        dataList.add(data);
    }
    return  dataList;
}



public  void deleteData(int ID){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_NAME, COLUMN1+" = "+ ID, null);
    db.close();
}

}



