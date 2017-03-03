package com.osu.cse5236.oddjobs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.osu.cse5236.oddjobs.JobDbSchema.*;

public class JobCollection {
    private static JobCollection sJobCollection;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static JobCollection get(Context context) {
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
        ContentValues values = getContentValues(j);

        mDatabase.insert(JobTable.NAME, null, values);
    }
    public List<Job> getJobs() {
        return new ArrayList<>();
    }

    public Job getJob(UUID id) {
        return null;
    }

    public void updateJob(Job job) {
        String uuidString = job.getId().toString();
        ContentValues values = getContentValues(job);

        mDatabase.update(JobTable.NAME, values,
                JobTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Job job) {
        ContentValues values = new ContentValues();
        values.put(JobTable.Cols.UUID, job.getId().toString());
        values.put(JobTable.Cols.TITLE, job.getTitle());
        values.put(JobTable.Cols.DESCRIPTION, job.getDescription());
        values.put(JobTable.Cols.DATE, job.getDate().toString());
        values.put(JobTable.Cols.PAID_HOURLY, job.isPaidHourly());
        values.put(JobTable.Cols.COMPENSATION, job.getCompensation().toString());
        values.put(JobTable.Cols.POSTER, job.getPoster().toString());
        values.put(JobTable.Cols.VOLUNTEER, job.getVolunteer().toString());

        return values;
    }
}
