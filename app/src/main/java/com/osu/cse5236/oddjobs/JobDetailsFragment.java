package com.osu.cse5236.oddjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.osu.cse5236.oddjobs.activities.EditJobActivity;
import com.osu.cse5236.oddjobs.activities.PayActivity;

import java.util.UUID;

/**
 * Created by Zenith on 3/24/2017.
 */

public class JobDetailsFragment extends Fragment {

    private static final String TAG = "JobDetailsFragment RAWR";
    private static final String ARG_JOB_ID = "job_id";
    private static final String EXTRA_JOB_ID = "com.osu.oddjobs.job_id";

    private Job mJob;

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

        TextView mTitleView;
        TextView mPosterView;
        TextView mDescriptionView;
        TextView mCompensationView;
        TextView mCityView;
        Button mVolunteerButton;
        TextView mVolunteerView;
        Button mEditButton;
        Button mPayButton;
        CheckBox mCompletedCheckbox;

        mTitleView = (TextView) v.findViewById(R.id.job_title);
        mTitleView.setText(mJob.getTitle());

        mPosterView = (TextView) v.findViewById(R.id.job_poster);
        Log.d(TAG, "poster is " + mJob.getPoster());
        if (mJob.getPoster() != null) {
            mPosterView.setText(mJob.getPoster());
        }

        mCompensationView = (TextView) v.findViewById(R.id.job_compensation);
        if (mJob.getCompensation() != null) {
            mCompensationView.setText(mJob.getCompensation());
        }

        mCityView = (TextView) v.findViewById(R.id.job_city);
        if (mJob.getCity() != null) {
            mCityView.setText(mJob.getCity());
        }

        mDescriptionView = (TextView) v.findViewById(R.id.job_description);
        if (mJob.getDescription() != null) {
            mDescriptionView.setText(mJob.getDescription());
        }

        mVolunteerButton = (Button) v.findViewById(R.id.volunteer_button);
        mVolunteerButton.setEnabled(true);

        mVolunteerView = (TextView) v.findViewById(R.id.job_volunteer);
        if (mJob.getVolunteer() != null) {
            mVolunteerView.setText(mJob.getVolunteer());
        }

        mCompletedCheckbox = (CheckBox)v.findViewById(R.id.job_completed);
        mCompletedCheckbox.setChecked(mJob.isCompleted());
        mCompletedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mJob.setCompleted(isChecked);
            }
        });

        mEditButton = (Button) v.findViewById(R.id.edit_job_button);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Edit button clicked");
                Intent intent = EditJobActivity.newIntent(getActivity(), mJob.getId());
                JobCollection.editJob = mJob.getId();
                intent.putExtra(EXTRA_JOB_ID, mJob.getId());
                startActivity(intent);
            }
        });

        mPayButton = (Button) v.findViewById(R.id.pay_button);
        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pay button clicked");
                Intent intent = new Intent(getActivity(), PayActivity.class);
                startActivity(intent);
            }
        });

        String userName = UserCollection.get(getActivity()).getCurrentUserFullName();
        Log.d(TAG, "current user's name is " + userName);

//        List<User> users = UserCollection.get(getActivity()).getUsers();
//        Log.d(TAG, "User collection:");
//        for (User user : users) {
//            if (user.getId() != null) {Log.d(TAG, "id: " + user.getId());}
//            if (user.getFirstName() != null) {Log.d(TAG, "first name: " + user.getFirstName());}
//            if (user.getLastName() != null) {Log.d(TAG, "last name: " + user.getLastName());}
//            if (user.getPhone() != null) {Log.d(TAG, "phone: " + user.getPhone());}
//            if (user.getEmail() != null) {Log.d(TAG, "email: " + user.getEmail());}
//            if (user.getPassword() != null) {Log.d(TAG, "password: " + user.getPassword());}
//            Log.d(TAG, " *********************** ");
//        }

        return v;
    }
}
