package com.osu.cse5236.oddjobs.activities;

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
import com.osu.cse5236.oddjobs.UserCollection;

import java.util.List;
import java.util.UUID;

/**
 * Created by Zenith on 3/26/2017.
 */

public class NewJobActivity extends AppCompatActivity {
    private static final String TAG = "NewJobActivity RAWR";
    private static final String EXTRA_JOB_ID = "com.osu.oddjobs.job_id";

    private Job mJob;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private EditText mCompensationField;
    private String enteredTitle;
    private String enteredDescription;
    private String enteredCompensation;
    private Button mCreateNewJobButton;
    private Context mContext = this;

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);
        mJob = new Job();

        mTitleField = (EditText) findViewById(R.id.job_title);
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

        mCreateNewJobButton = (Button) findViewById(R.id.create_new_job_button);
        mCreateNewJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Create New Job button clicked");

                String userName = UserCollection.get(mContext).getCurrentUserFullName();
                Log.d(TAG, "current user's name is " + userName);
                mJob.setTitle(enteredTitle);
                mJob.setPoster(UserCollection.mCurrentUserFullName);
                mJob.setPosterPhone(UserCollection.currentUserPhone);
                mJob.setPosterEmail(UserCollection.currentUserEmail);

                Log.d(TAG, "UserCollection.currentUserPhone is " + UserCollection.currentUserPhone);
                Log.d(TAG, "UserCollection.currentUserEmail is " + UserCollection.currentUserEmail);
                Log.d(TAG, "mJob.getPosterPhone() is " + mJob.getPosterPhone());
                Log.d(TAG, "mJob.getPosterEmail() is " + mJob.getPosterEmail());

                mJob.setDescription(enteredDescription);
                mJob.setCompensation(enteredCompensation);

                mJob.setCity(UserCollection.currentUserCity);
                mJob.setLongitude(UserCollection.currentUserLongitude);
                mJob.setLatitude(UserCollection.currentUserLatitude);

//                Log.d(TAG, "JobCollection.currentJobLatitude is " + JobCollection.currentJobLatitude);
//                Log.d(TAG, "JobCollection.currentJobLongitude is " + JobCollection.currentJobLongitude);
//                Log.d(TAG, "mJob.getLatitude() is " + mJob.getLatitude());
//                Log.d(TAG, "mJob.getLongitude() is " + mJob.getLongitude());

                JobCollection.get(mContext).addJob(mJob);
                finish();

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
                    Log.d(TAG, " ****************** ");
                }
            }
        });
    }

    public static Intent newIntent(Context packageContext, UUID jobId) {
        Log.d(TAG, "newIntent() called");
        Intent intent = new Intent(packageContext, NewJobActivity.class);
        intent.putExtra(EXTRA_JOB_ID, jobId);
        return intent;
    }
}
