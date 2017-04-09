package com.osu.cse5236.oddjobs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.osu.cse5236.oddjobs.database.JobBaseHelper;
import com.osu.cse5236.oddjobs.database.JobCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.osu.cse5236.oddjobs.database.JobDbSchema.JobTable;

/*
 * Job Collection Singleton
 */
public class JobCollection {
    private static final String TAG = "JobCollection RAWR";

    private static JobCollection sJobCollection;

    // These variables are used as "global" variables, for the current job being looked at.
    // It feels hacky and not ideal; maybe refactor when possible.
    public static UUID editJob;
    public static double currentJobLongitude = 0.0;
    public static double currentJobLatitude = 0.0;
    public static Job currentJob;
    public static String jobPosterName = "";
    public static String jobPosterPhone = "";
    public static String jobPosterEmail = "";

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static JobCollection get(Context context) {
        Log.d(TAG, "get() called");
        if (sJobCollection == null) {
            sJobCollection = new JobCollection(context);
        }
        return sJobCollection;
    }

    private JobCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new JobBaseHelper(mContext).getWritableDatabase();
    }

    public void addJob(Job j) {
        Log.d(TAG, "addJob() called");
        ContentValues values = getContentValues(j);
        mDatabase.insert(JobTable.NAME, null, values);
    }

    public void deleteJob(Job j) {
        Log.d(TAG, "deleteJob() called");
        ContentValues values = getContentValues(j);
        mDatabase.delete(JobTable.NAME, "uuid=?", new String[] {String.valueOf(j.getId())});
    }

    public List<Job> getJobs() {
        Log.d(TAG, "getJobs() called");
        List<Job> jobs = new ArrayList<>();

        JobCursorWrapper cursor = queryJobs(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                jobs.add(cursor.getJob());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return jobs;
    }

    public Job getJob(UUID id) {
        Log.d(TAG, "getJob() called");
        JobCursorWrapper cursor = queryJobs(
                JobTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getJob();
        } finally {
            cursor.close();
        }
    }

    public void updateJob(Job job) {
        Log.d(TAG, "updateJob() called");
        String uuidString = job.getId().toString();
        ContentValues values = getContentValues(job);

        mDatabase.update(JobTable.NAME, values,
                JobTable.Cols.UUID + " = ?",
                new String[] {uuidString });
    }

    private JobCursorWrapper queryJobs(String whereClause, String[] whereArgs) {
        Log.d(TAG, "queryJobs() called");
        Cursor cursor = mDatabase.query(
                JobTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new JobCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Job job) {
        Log.d(TAG, "getContentValues() called");
        ContentValues values = new ContentValues();
        values.put(JobTable.Cols.UUID, job.getId().toString());
        values.put(JobTable.Cols.TITLE, job.getTitle());
        values.put(JobTable.Cols.POSTER, job.getPoster());
        values.put(JobTable.Cols.POSTER_PHONE, job.getPosterPhone());
        values.put(JobTable.Cols.POSTER_EMAIL, job.getPosterEmail());
        values.put(JobTable.Cols.COMPENSATION, job.getCompensation());
        values.put(JobTable.Cols.DESCRIPTION, job.getDescription());
        values.put(JobTable.Cols.CITY, job.getCity());
        values.put(JobTable.Cols.LONGITUDE, job.getCity());
        values.put(JobTable.Cols.LATITUDE, job.getCity());
        values.put(JobTable.Cols.COMPLETED, job.isCompleted() ? 1 : 0);
//        values.put(JobTable.Cols.DATE, job.getDate().getTime());

        return values;
    }

}
