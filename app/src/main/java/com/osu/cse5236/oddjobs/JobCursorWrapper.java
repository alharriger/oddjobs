package com.osu.cse5236.oddjobs;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import static com.osu.cse5236.oddjobs.JobDbSchema.JobTable;

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobCursorWrapper extends CursorWrapper {
    public JobCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Job getJob() {
        String uuid = getString(getColumnIndex(JobTable.Cols.UUID));
        String title = getString(getColumnIndex(JobTable.Cols.TITLE));
        String compensation = getString(getColumnIndex(JobTable.Cols.COMPENSATION));
        String description = getString(getColumnIndex(JobTable.Cols.DESCRIPTION));
        String poster = getString(getColumnIndex(JobTable.Cols.POSTER));

        Job job = new Job(UUID.fromString(uuid));
        job.setTitle(title);
        job.setCompensation(Float.parseFloat(compensation));
        job.setDescription(description);
        job.setPoster(poster);

        return job;
    }
}
