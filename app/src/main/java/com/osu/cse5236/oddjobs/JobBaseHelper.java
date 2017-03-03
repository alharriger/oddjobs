package com.osu.cse5236.oddjobs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.osu.cse5236.oddjobs.JobDbSchema.JobTable;

public class JobBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "jobBase.db";

    public JobBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + JobTable.NAME + "(" +
                "_id integer primary key autoincrement," +
                JobTable.Cols.UUID + ", " +
                JobTable.Cols.TITLE + ", " +
                JobTable.Cols.DESCRIPTION + ", " +
                JobTable.Cols.DATE + ", " +
                JobTable.Cols.PAID_HOURLY + ", " +
                JobTable.Cols.COMPENSATION + ", " +
                JobTable.Cols.POSTER + ", " +
                JobTable.Cols.VOLUNTEER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
