package com.example.heavymachinery.utilites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Products.db";
    public static final String TABLE_NAME = "product";

    public static final String Id = "id";
    public static final String Name = "machineName";
    public static final String ModelNo = "modelNo";
    public static final String Stock = "stock";
    public static final String Price = "price";



    public static final int version =1;


    //Create Table
    String createQuery = "create table " + TABLE_NAME + "(" + Id
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Name + " TEXT NOT NULL, " + ModelNo + " TEXT NOT NULL, "
            + Stock + " TEXT NOT NULL, "+Price+" REAL NOT NULL);";

    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    //To Insert Data
    public Boolean insertData(String name, String modelNo, String stock, Float price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name,name);
        values.put(ModelNo,modelNo);
        values.put(Stock,stock);
        values.put(Price,price);
        long result = db.insert(TABLE_NAME,null,values);
        db.close();
        return result != -1;
    }

    public Boolean Update(Integer upId, String upName, String upModelNo, String upStock, Float upPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name,upName);
        values.put(ModelNo,upModelNo);
        values.put(Stock,upStock);
        values.put(Price,upPrice);
        long result = db.update(TABLE_NAME,values,"ID = ?",new String[]{upId.toString()});
        return result != -1;
    }

    public Boolean deleteData(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id.toString()});
        return result != -1;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    public Cursor getTableData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME+" where "+Id+"= ?",new String[]{String.valueOf(id)},null);
    }

    public void cleanData(String tableName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);

    }

    public Boolean clearData(){

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM  " + TABLE_NAME);
            return true;
        }catch(Exception e){
            return false;
        }

    }



}
