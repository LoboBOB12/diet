package com.noexerciseweightlosst.wqxw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


class DatabaseManager {

    // database column NAMES e.g. rowID
    private static final String KEY_ROWID 	    = "_id";
    private static final String KEY_USERNAME = "user_username";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_AGE = "user_age";
    private static final String KEY_HEIGHT = "user_height";
    private static final String KEY_WEIGHT = "user_weight";
    private static final String KEY_GENDER = "user_gender";

    private static final String DATABASE_TABLE 	= "Users";
    private static final String DATABASE_NAME 	= "DietBuddy";
    private static final int DATABASE_VERSION 	= 1; // since it is the first version of the dB

    // SQL statement to create the database
    private static final String DATABASE_CREATE =
            "CREATE table Users (_id integer primary key autoincrement, " +
                    "user_username text not null, " + "user_password text not null, " +
                    "user_age integer not null, " + "user_height float not null, " +
                    "user_weight float not null, " + "user_gender String not null"+")";

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    DatabaseManager(Context context) {

            DBHelper        = new DatabaseHelper(context);
    }

    SQLiteDatabase open() throws SQLException
    {
        //opens the database
        db     = DBHelper.getWritableDatabase();
        return db;

    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            // dB structure change..

        }
    }

    void close()

    {
        //close the database
        DBHelper.close();
    }

    // for inserting a user
    long insertUser(String username, String password, String age, Float weight, Float height, String gender){

        //method to insert a row onto the table
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_AGE, age);
        initialValues.put(KEY_WEIGHT, height);
        initialValues.put(KEY_HEIGHT, weight);
        initialValues.put(KEY_GENDER, gender);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }



    Cursor checkLogin(String un) throws SQLException
    {

        Log.e("Inside checkLogin",un);
        Cursor mCursor =   db.query(true, DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                KEY_USERNAME,
                                KEY_PASSWORD

                        },
                KEY_USERNAME + " = '" +un+"'",  null, null, null, null, null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();

        }

        return mCursor;
    }

     Cursor getUser(long rowId) throws SQLException
    {


        Log.e("Inside getUser", Float.toString(rowId));

        Cursor mCursor =   db.query(true, DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                KEY_USERNAME,
                                KEY_AGE,
                                KEY_HEIGHT,
                                KEY_WEIGHT,
                                KEY_GENDER

                        },
                KEY_ROWID + " = '" +rowId+"'",  null, null, null, null, null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }

        return mCursor;
    }


}
