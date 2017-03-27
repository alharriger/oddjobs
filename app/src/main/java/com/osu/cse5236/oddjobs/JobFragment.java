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

import java.util.UUID;

/**
 * Created by Zenith on 3/24/2017.
 */

public class JobFragment extends Fragment {

    private static final String ARG_JOB_ID = "job_id";
    private static final String TAG = "JobFragment";

    private Job mJob;
    private EditText mTitleField;
    private EditText mCompensationField;
    private EditText mDescriptionField;
    private Button mVolunteerButton;
    private CheckBox mCompletedCheckbox;

    public static JobFragment newInstance(UUID jobId) {
        Log.d(TAG, "newInstance() called");
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID, jobId);

        JobFragment fragment = new JobFragment();
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
        View v = inflater.inflate(R.layout.fragment_job, container, false);

        mTitleField = (EditText) v.findViewById(R.id.job_title);
        mTitleField.setText(mJob.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mCompensationField = (EditText) v.findViewById(R.id.job_compensation);
        mCompensationField.setText(mJob.getCompensation().toString());
        mCompensationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.setCompensation(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mDescriptionField = (EditText) v.findViewById(R.id.job_description);
        mDescriptionField.setText(mJob.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.setDescription(s.toString());
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

        return v;
    }
}
