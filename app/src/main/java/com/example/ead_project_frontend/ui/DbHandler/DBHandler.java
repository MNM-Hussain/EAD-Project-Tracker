package com.example.ead_project_frontend.ui.DbHandler;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ead_project_frontend.config.Session;

public class DBHandler extends SQLiteOpenHelper {
    //*setting variable for Login and this value will be the database name:
    public static final String DB_NAME = "UserDB.db";
    public DBHandler(Context context) {
        //Another Db name and version is mentioned
        super(context, "UserDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //to execute sql query
        DB.execSQL("create Table UserRegistration (userName TEXT, nic TEXT primary key, email TEXT unique, password TEXT, vehicleType TEXT, fuelType TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserRegistration");
    }

    //********************************************Registration**********************************************************************************
    //----------Insert----------------------------------------
    public Boolean insertUserRegistration(String userName, String nic, String email, String password, String vehicleType, String fuelType) {
        // it get the data repository in write mode
        SQLiteDatabase DB = this.getWritableDatabase();

        //create a new map of values,where column names the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("nic", nic);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("vehicleType", vehicleType);
        contentValues.put("fuelType", fuelType);
//        contentValues.put("dieselType", dieselType);

        // to insert the new row returning the new primary key
        long result = DB.insert("UserRegistration", null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //-------Update-----------------
    public Boolean updateUserRegistration(String userName, String nic, String email, String password, String vehicleType, String fuelType) {
        // it get the data repository in write mode
        SQLiteDatabase DB = this.getWritableDatabase();

        //create a new map of values,where column names the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
//        contentValues.put("nic", nic);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("vehicleType", vehicleType);
        contentValues.put("fuelType", fuelType);

        //Cursor is like selecting the row
        Cursor cursor  = DB.rawQuery("Select * from Registration where nic = ?", new String[] {nic});

        if (cursor.getCount() > 0) {

            // updating the table using NIC
            long result = DB.update("Registration", contentValues, "nic = ?", new String[]{nic});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //-------Delete-----------------
    public Boolean deleteUserRegistration(String nic) {
        // it get the data repository in write mode
        SQLiteDatabase DB = this.getWritableDatabase();

        //Cursor is like selecting the row
        Cursor cursor  = DB.rawQuery("Select * from Registration where nic = ?", new String[] {nic});

        if (cursor.getCount() > 0) {
            // Delete the table using NIC
            long result = DB.delete("Registration","nic = ?", new String[]{nic});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //-------Retrieve-----------------
//    public Cursor getUserRegistration() {
//        // it get the data repository in write mode
//        SQLiteDatabase DB = this.getWritableDatabase();
//
//        //Cursor is like selecting the row
//        Cursor cursor  = DB.rawQuery("Select * from Registration", null);
//        return cursor;
//    }

    //**********************************************Login*****************************************************************
    public Boolean checkUsername (String userName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserRegistration where userName = ?", new String[] {userName} );
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkExistingUser (String nic, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserRegistration where nic = ? and email = ?", new String[] { nic, email } );
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public Boolean checkUserCredentials (String email, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserRegistration where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                Session.USER_NAME = cursor.getString(cursor.getColumnIndex("userName"));
                Session.NIC = cursor.getString(cursor.getColumnIndex("nic"));
                Session.USER_EMAIL = cursor.getString(cursor.getColumnIndex("email"));
                Session.VECHILE_TYPE = cursor.getString(cursor.getColumnIndex("vehicleType"));
                Session.FUEL_TYPE = cursor.getString(cursor.getColumnIndex("fuelType"));
                System.out.println(Session.VECHILE_TYPE);
            }
            return true;
        }
        else
            return false;
    }

    //******************Profile view Retreive *********************************

    public Cursor getUserRegistration() {
        // it get the data repository in write mode
//        SQLiteDatabase DB = this.getWritableDatabase();
        SQLiteDatabase DB = this.getReadableDatabase();
        //Cursor is like selecting the row
        Cursor cursor  = DB.rawQuery("Select * from UserRegistration" , null);
        return cursor;
    }
}


