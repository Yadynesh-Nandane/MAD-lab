package com.example.androidlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "EmpData.db";

    public DBHelper(Context context) {
        super(context, "EmpData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase EmpDB) {
        EmpDB.execSQL("create Table Employee (ID TEXT primary key,Name TEXT,Address TEXT,UserName TEXT, Password TEXT, LoginTime TEXT, LogoutTime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase EmpDB, int i, int i1) {
        EmpDB.execSQL("drop Table if exists Employee ");
    }

    public Boolean insertData(String ID, String Name, String Address, String UserName, String Password, String logintime, String logouttime) {
        SQLiteDatabase EmpDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("Name", Name);
        contentValues.put("Address", Address);
        contentValues.put("UserName", UserName);
        contentValues.put("Password", Password);
        contentValues.put("LoginTime", logintime);
        contentValues.put("LogoutTime", logouttime);
        long result = EmpDB.insert("Employee",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updatelogintime(String UserName, String logintime){
        SQLiteDatabase EmpDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LoginTime", logintime);
        Cursor cursor = EmpDB.rawQuery("select * from Employee where UserName=?",new String[] {UserName});
        if(cursor.getCount()>0) {
            long result = EmpDB.update("Employee", contentValues, "UserName=?", new String[] {UserName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Boolean updatelogouttime(String email, String logouttime){
        SQLiteDatabase EmpDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LogoutTime", logouttime);
        Cursor cursor = EmpDB.rawQuery("select * from Employee where UserName=?",new String[] {email});
        if(cursor.getCount()>0) {
            long result = EmpDB.update("Employee", contentValues, "UserName=?", new String[]{email});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getdata (){
        SQLiteDatabase EmpDB =this.getWritableDatabase();
        Cursor cursor = EmpDB.rawQuery("select ID,Name from Employee where LoginTime=?",new String[] {"NA"});
        return cursor;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase EmpDB = this.getWritableDatabase();
        Cursor cursor = EmpDB.rawQuery("select * from Employee where username=?",new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase EmpDB = this.getWritableDatabase();
        Cursor cursor = EmpDB.rawQuery("select * from Employee where username= ? and password= ?",new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
}
