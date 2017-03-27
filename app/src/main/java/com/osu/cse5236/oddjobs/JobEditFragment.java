package com.osu.cse5236.oddjobs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Zenith on 3/27/2017.
 */

public class JobEditFragment extends Fragment {
    private static final String ARG_JOB_ID = "job_id";
    private static final String TAG = "JobEditFragment RAWR";

    private Job mJob;
    private TextView mTitleField;
    private TextView mCompensationField;
    private TextView mDescriptionField;
    private String enteredTitle;
    private String enteredCompensation;
    private String enteredDescription;
    private Button mVolunteerButton;
    private CheckBox mCompletedCheckbox;
    private Button mUpdateJobButton;
    private Button mDeleteJobButton;

    public static JobDetailsFragment newInstance(UUID jobId) {
        Log.d(TAG, "newInstance() called");
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID, jobId);

        JobDetailsFragment fragment = new JobDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        UUID jobId = (UUID) getArguments().getSerializable(ARG_JOB_ID);
        mJob = JobCollection.get(getActivity()).getJob(jobId);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
        JobCollection.get(getActivity()).updateJob(mJob);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");
        View v = inflater.inflate(R.layout.fragment_job_details, container, false);

        mTitleField = (EditText) v.findViewById(R.id.job_title);
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

        mDescriptionField = (TextView) v.findViewById(R.id.job_description);
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

        mCompensationField = (TextView) v.findViewById(R.id.job_compensation);
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

        mVolunteerButton = (Button) v.findViewById(R.id.volunteer_button);
        mVolunteerButton.setEnabled(true);

        mCompletedCheckbox = (CheckBox)v.findViewById(R.id.job_completed);
        mCompletedCheckbox.setChecked(mJob.isCompleted());
        mCompletedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mJob.setCompleted(isChecked);
            }
        });

        mUpdateJobButton = (Button) v.findViewById(R.id.update_job_button);
        mUpdateJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Update Job button clicked");
//                mJob.setTitle(enteredTitle);
//                mJob.setCompensation(enteredCompensation);
//                mJob.setDescription(enteredDescription);
//                JobCollection.get(mContext).addJob(mJob); // TODO: edit, not add
            }
        });

        mDeleteJobButton = (Button) v.findViewById(R.id.delete_job_button);
        mDeleteJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Delete Job button clicked");
//                JobCollection.get(mContext).deleteJob(mJob);
            }
        });

        return v;
    }

}
