package com.example.projectmadlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME="Register.db";
    public  static final String TABLE_NAME ="Register";
    public static  final String Kolom_1="ID";
    public static  final String Kolom_2="FullName";
    public static  final String Kolom_3="Email";
    public static  final String Kolom_4="Password";
    public static  final String Kolom_5="Phone";


    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_NAME +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FullName TEXT, Email TEXT, Password Text, Phone Integer)";


    public databaseHelper(@Nullable Context context) {
        super(context, "DATABASE_NAME", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public  long addUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.Kolom_3, email);
        contentValues.put(databaseHelper.Kolom_4, password);
        long res = db.insert("Register", null, contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser (String email, String password){
        String[] columns = {Kolom_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Kolom_3 + "=?" + " and " + Kolom_4 + "=?";
        String[] selectionArgs = { email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}

