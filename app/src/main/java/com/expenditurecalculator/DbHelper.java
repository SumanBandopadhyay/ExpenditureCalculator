package com.expenditurecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by sony on 26-03-2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TESTO.db";
    public static final String TABLE_NAME = "TEST_TABLE";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TEST_TABLE(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TYPE TEXT, AMOUNT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TEST.db");
        onCreate(db);
    }

    public boolean insertIntoTable(String date, String type, String amount) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("TYPE", type);
        contentValues.put("AMOUNT", amount);
        database.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList getData() {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM TEST_TABLE";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<String> arrayList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("TYPE"))+ " : " +cursor.getString(cursor.getColumnIndex("AMOUNT")) + " : " + cursor.getString(cursor.getColumnIndex("DATE")));
            cursor.moveToNext();
        }

        return arrayList;
    }

    public ArrayList getDateElement(String dt) {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM TEST_TABLE WHERE DATE='"+dt+"'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<String> arrayList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("TYPE")) + ":" + cursor.getString(cursor.getColumnIndex("AMOUNT")));
            cursor.moveToNext();
        }

        return arrayList;
    }

}
