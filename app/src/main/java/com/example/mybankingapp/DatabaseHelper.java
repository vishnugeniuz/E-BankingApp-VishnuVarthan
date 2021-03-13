package com.example.mybankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9065461310,'Sachin',9972.00,'sachin@gmail.com','XXXXXXXXXXXX1004','ABC09876543')");
        db.execSQL("insert into user_table values(9117484221,'Dhoni',5825.77,'dhoni@gmail.com','XXXXXXXXXXXX3341','ABC09876543')");
        db.execSQL("insert into user_table values(8455245522,'Raina',10000,'raina@gmail.com','XXXXXXXXXXXX3552','ABC09876543')");
        db.execSQL("insert into user_table values(9454584544,'Rohit',9866.20,'rohit@gmail.com','XXXXXXXXXXXX2522','ABC09876543')");
        db.execSQL("insert into user_table values(9554512155,'Jadeja',2550.16,'jadeja@gmail.com','XXXXXXXXXXXX3332','ABC09876543')");
        db.execSQL("insert into user_table values(9612357566,'Pant',5006.00,'pant@gmail.com','XXXXXXXXXXXX3434','ABC09876543')");
        db.execSQL("insert into user_table values(9774684657,'Rahul',8000.20,'rahul@gmail.com','XXXXXXXXXXXX1204','ABC09876543')");
        db.execSQL("insert into user_table values(9854545468,'Pandya',4008.26,'pandya@gmail.com','XXXXXXXXXXXX3778','ABC09876543')");
        db.execSQL("insert into user_table values(9445879213,'Natrajan',2730.50,'natrajan@gmail.com','XXXXXXXXXXXX4203','ABC09876543')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
