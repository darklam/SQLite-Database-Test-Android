package com.john.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by giann on 13-Jun-17.
 */

public class AgeDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Age.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AgeContract.AgeEntry.TABLE_NAME + " ("
            + AgeContract.AgeEntry._ID + " INTEGER PRIMARY KEY,"
            + AgeContract.AgeEntry.COLUMN_NAME_AGE + " REAL,"
            + AgeContract.AgeEntry.COLUMN_NAME_NAME + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + AgeContract.AgeEntry.TABLE_NAME;

    public AgeDbHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteDatabase(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
