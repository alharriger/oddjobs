package com.osu.cse5236.oddjobs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.osu.cse5236.oddjobs.database.JobDbSchema.JobTable;

public class JobBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "JobBaseHelper";
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "jobBase.db";

    public JobBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called");
        db.execSQL("create table " + JobTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                JobTable.Cols.UUID + "," +
                JobTable.Cols.TITLE + "," +
                JobTable.Cols.COMPENSATION + "," +
                JobTable.Cols.DESCRIPTION + "," +
                JobTable.Cols.COMPLETED + "," +
                JobTable.Cols.DATE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called");
    }
}