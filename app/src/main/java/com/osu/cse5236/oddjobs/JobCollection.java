package com.osu.cse5236.oddjobs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobCollection {
    private static JobCollection sJobCollection;
    private List<Job> mJobs;
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
        mJobs = new ArrayList<>();
    }

    public List<Job> getJobs() {
        return mJobs;
    }

    public Job getJob(UUID id) {
        for (Job job : mJobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }
}
