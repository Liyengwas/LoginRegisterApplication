package com.example.carsloginandregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="cars.db";
    public static final String TABLE_NAME ="users";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="names";
    public static final String COL_3 ="username";
    public static final String COL_4 ="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(ID INTEGER PRIMARY KEY AUTOINCREMENT,names TEXT,username TEXT,password TEXT)");//create a new table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);//check to see if table exists and we drop it if it exists
        onCreate(sqLiteDatabase);

    }

    public long addUser(String names,String user,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("names",names);
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("users" ,null,contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username,String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_3 + "=?" + "and" + COL_4 + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count =cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }

}
