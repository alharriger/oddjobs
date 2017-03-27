package com.osu.cse5236.oddjobs;

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

import java.util.UUID;

/**
 * Created by Zenith on 3/26/2017.
 */

public class NewJobActivity extends AppCompatActivity {
    private static final String TAG = "NewJobActivity";
    private static final String EXTRA_JOB_ID = "com.osu.oddjobs.job_id";

    private Job mJob;
    private EditText mTitleField;
    private EditText mCompensationField;
    private EditText mDescriptionField;
    private String enteredTitle;
    private String enteredCompensation;
    private String enteredDescription;
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
                mJob.setTitle(enteredTitle);
                mJob.setCompensation(enteredCompensation);
                mJob.setDescription(enteredDescription);
                JobCollection.get(mContext).addJob(mJob);
                finish();
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
