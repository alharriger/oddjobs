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
    private static final String TAG = "JobCursorWrapper RAWR";

    public JobCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Job getJob() {
        Log.d(TAG, "getJob() called");
        String uuidString = getString(getColumnIndex(JobTable.Cols.UUID));
        String title = getString(getColumnIndex(JobTable.Cols.TITLE));
        String poster = getString(getColumnIndex(JobTable.Cols.POSTER));
        String posterPhone = getString(getColumnIndex(JobTable.Cols.POSTER_PHONE));
        String posterEmail = getString(getColumnIndex(JobTable.Cols.POSTER_EMAIL));
        String description = getString(getColumnIndex(JobTable.Cols.DESCRIPTION));
        String compensation = getString(getColumnIndex(JobTable.Cols.COMPENSATION));
        String city = getString(getColumnIndex(JobTable.Cols.CITY));
        double longitude = getDouble(getColumnIndex(JobTable.Cols.LONGITUDE));
        double latitude = getDouble(getColumnIndex(JobTable.Cols.LATITUDE));
        String volunteer = getString(getColumnIndex(JobTable.Cols.VOLUNTEER));
        String volunteerPhone = getString(getColumnIndex(JobTable.Cols.VOLUNTEER_PHONE));
        String volunteerEmail = getString(getColumnIndex(JobTable.Cols.VOLUNTEER_EMAIL));
        int isCompleted = getInt(getColumnIndex(JobTable.Cols.COMPLETED));
        long date = getLong(getColumnIndex(JobTable.Cols.DATE));

        Job job = new Job(UUID.fromString(uuidString));
        job.setTitle(title);
        job.setPoster(UUID.fromString(poster));
        job.setPosterPhone(posterPhone);
        job.setPosterEmail(posterEmail);
        job.setDescription(description);
        job.setCompensation(compensation);
        job.setCity(city);
        job.setLongitude(longitude);
        job.setLatitude(latitude);
        job.setVolunteer(volunteer);
        job.setVolunteerPhone(volunteerPhone);
        job.setVolunteerEmail(volunteerEmail);
        job.setCompleted(isCompleted != 0);
        job.setDate(new Date(date));

        return job;
    }
}