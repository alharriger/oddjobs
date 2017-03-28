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
                JobCollection.get(mContext).updateJob(mJob);
                finish();
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
