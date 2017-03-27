package com.osu.cse5236.oddjobs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pavlecoric on 3/23/17.
 */

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserDbSchema.UserTable.NAME + "(" +
                "_id integer primary key autoincrement," +
                UserDbSchema.UserTable.Cols.UUID + ", " +
                UserDbSchema.UserTable.Cols.FIRSTNAME + ", " +
                UserDbSchema.UserTable.Cols.LASTNAME + ", " +
                UserDbSchema.UserTable.Cols.EMAIL + ", " +
                UserDbSchema.UserTable.Cols.PHONE + ", " +
                UserDbSchema.UserTable.Cols.PASSWORD + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

