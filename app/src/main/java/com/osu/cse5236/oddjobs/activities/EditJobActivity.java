package com.osu.cse5236.oddjobs.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.osu.cse5236.oddjobs.Job;
import com.osu.cse5236.oddjobs.JobCollection;
import com.osu.cse5236.oddjobs.R;
import com.osu.cse5236.oddjobs.database.JobDbSchema;

import java.util.List;
import java.util.UUID;

/**
 * Created by Zenith on 3/27/2017.
 */

public class EditJobActivity extends AppCompatActivity {

    private static final String TAG = "EditJobActivity RAWR";

    private Job mJob;
    private EditText mTitleField;
    private EditText mCompensationField;
    private EditText mDescriptionField;
    private String enteredTitle;
    private String enteredCompensation;
    private String enteredDescription;
    private Button mUpdateJobButton;
    private Button mDeleteJobButton;
    private Context mContext = this;

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_job);

        if (JobCollection.editJob != null) {
            mJob = JobCollection.get(this).getJob(JobCollection.editJob);
        }

        mTitleField = (EditText) findViewById(R.id.job_title);
        if (mJob.getTitle() != null) {
            mTitleField.setText(mJob.getTitle());
        }
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredTitle = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mDescriptionField = (EditText) findViewById(R.id.job_description);
        if (mJob.getDescription() != null) {
            mDescriptionField.setText(mJob.getDescription());
        }
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredDescription = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mCompensationField = (EditText) findViewById(R.id.job_compensation);
        if (mJob.getCompensation() != null) {
            mCompensationField.setText(mJob.getCompensation());
        }
        mCompensationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredCompensation = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mUpdateJobButton = (Button) findViewById(R.id.update_job_button);
        mUpdateJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Edit Job button clicked");
                mJob.setTitle(enteredTitle);
                mJob.setCompensation(enteredCompensation);
                mJob.setDescription(enteredDescription);
                ContentValues values = new ContentValues();

                values.put(JobDbSchema.JobTable.Cols.UUID, mJob.getId().toString());
                values.put(JobDbSchema.JobTable.Cols.POSTER, mJob.getPoster());
                values.put(JobDbSchema.JobTable.Cols.POSTER_PHONE, mJob.getPosterPhone());
                values.put(JobDbSchema.JobTable.Cols.POSTER_EMAIL, mJob.getPosterEmail());
                values.put(JobDbSchema.JobTable.Cols.CITY, mJob.getCity());
                values.put(JobDbSchema.JobTable.Cols.LONGITUDE, mJob.getLongitude());
                values.put(JobDbSchema.JobTable.Cols.LATITUDE, mJob.getLatitude());
                values.put(JobDbSchema.JobTable.Cols.COMPLETED, mJob.isCompleted() ? 1 : 0);

                values.put(JobDbSchema.JobTable.Cols.TITLE, enteredTitle);
                values.put(JobDbSchema.JobTable.Cols.COMPENSATION, enteredCompensation);
                values.put(JobDbSchema.JobTable.Cols.DESCRIPTION, enteredDescription);
                JobCollection.get(mContext).updateJob(mJob, values);


                finish();

                if (mJob.getId() != null) {Log.d(TAG, "mJob id: " + mJob.getId());}
                if (mJob.getTitle() != null) {Log.d(TAG, "mJob title: " + mJob.getTitle());}
                if (mJob.getPoster() != null) {Log.d(TAG, "mJob poster: " + mJob.getPoster());}
                if (mJob.getPosterPhone() != null) {Log.d(TAG, "mJob poster phone: " + mJob.getPosterPhone());}
                if (mJob.getPosterEmail() != null) {Log.d(TAG, "mJob poster email: " + mJob.getPosterEmail());}
                if (mJob.getDescription() != null) {Log.d(TAG, "mJob description: " + mJob.getDescription());}
                if (mJob.getCompensation() != null) {Log.d(TAG, "mJob compensation: " + mJob.getCompensation());}
                Log.d(TAG, "latitude: " + mJob.getLatitude());
                Log.d(TAG, "longitude: " + mJob.getLongitude());
                Log.d(TAG, " ****************** ");
                Log.d(TAG, " ****************** ");

                // List all jobs for debugging
                List<Job> jobs = JobCollection.get(mContext).getJobs();
                for (Job job : jobs) {
                    if (job.getId() != null) {Log.d(TAG, "id: " + job.getId());}
                    if (job.getTitle() != null) {Log.d(TAG, "title: " + job.getTitle());}
                    if (job.getPoster() != null) {Log.d(TAG, "poster: " + job.getPoster());}
                    if (job.getPosterPhone() != null) {Log.d(TAG, "poster phone: " + job.getPosterPhone());}
                    if (job.getPosterEmail() != null) {Log.d(TAG, "poster email: " + job.getPosterEmail());}
                    if (job.getDescription() != null) {Log.d(TAG, "description: " + job.getDescription());}
                    if (job.getCompensation() != null) {Log.d(TAG, "compensation: " + job.getCompensation());}
                    Log.d(TAG, "latitude: " + job.getLatitude());
                    Log.d(TAG, "longitude: " + job.getLongitude());
                    Log.d(TAG, " ****************** ");
                }
            }
        });

        mDeleteJobButton = (Button) findViewById(R.id.delete_job_button);
        mDeleteJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Delete Job button clicked");
                JobCollection.get(mContext).deleteJob(mJob);
                finish();
            }
        });
    }

    public static Intent newIntent(Context packageContext, UUID jobId) {
        Log.d(TAG, "newIntent() called");
        Intent intent = new Intent(packageContext, EditJobActivity.class);
        return intent;
    }
}
