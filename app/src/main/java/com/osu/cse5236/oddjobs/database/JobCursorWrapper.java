package com.osu.cse5236.oddjobs.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.osu.cse5236.oddjobs.Job;
import com.osu.cse5236.oddjobs.database.JobDbSchema.JobTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobCursorWrapper extends CursorWrapper {
    private static final String TAG = "JobCursorWrapper";

    public JobCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Job getJob() {
        Log.d(TAG, "getJob() called");
        String uuidString = getString(getColumnIndex(JobTable.Cols.UUID));
        String title = getString(getColumnIndex(JobTable.Cols.TITLE));
        String compensation = getString(getColumnIndex(JobTable.Cols.COMPENSATION));
        String description = getString(getColumnIndex(JobTable.Cols.DESCRIPTION));
        long date = getLong(getColumnIndex(JobTable.Cols.DATE));
        int isCompleted = getInt(getColumnIndex(JobTable.Cols.COMPLETED));

        Job job = new Job(UUID.fromString(uuidString));
        job.setTitle(title);
        job.setDate(new Date(date));
        job.setCompleted(isCompleted != 0);

        return job;
    }
}