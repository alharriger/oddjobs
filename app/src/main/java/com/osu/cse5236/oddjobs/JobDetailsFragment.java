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
import com.osu.cse5236.oddjobs.activities.JobMapActivity;
import com.osu.cse5236.oddjobs.activities.ThankVolunteerActivity;
import com.osu.cse5236.oddjobs.database.UserCursorWrapper;

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
        Button mViewMapButton;
        Button mVolunteerButton;
        TextView mVolunteerView;
        Button mEditButton;
        CheckBox mCompletedCheckbox;

        mTitleView = (TextView) v.findViewById(R.id.job_title);
        mTitleView.setText(mJob.getTitle());

        mPosterView = (TextView) v.findViewById(R.id.job_poster);
        Log.d(TAG, "poster is " + mJob.getPoster());
        if (mJob.getPoster() != null) {
            String posterId = mJob.getPoster().toString();
            String where = "UUID = ?";
            String[] whereArgs= new String[1];
            whereArgs[0] = posterId;
            UserCursorWrapper c = UserCollection.get(getContext()).queryJobs(where, whereArgs);
            c.moveToFirst();
            User user_poster = c.getUser();
            mPosterView.setText(user_poster.getFirstName() + " "+ user_poster.getLastName());
            JobCollection.jobPosterName = user_poster.getFirstName() + " "+ user_poster.getLastName();
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

        mViewMapButton = (Button) v.findViewById(R.id.view_map_button);
        mViewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "View map button clicked");
                JobCollection.currentJobLatitude = mJob.getLatitude();
                JobCollection.currentJobLongitude = mJob.getLongitude();
                Log.d(TAG, "mJob.getLatitude() is " + mJob.getLatitude());
                Log.d(TAG, "mJob.getLongitude() is " + mJob.getLongitude());
                Log.d(TAG, "JobCollection.currentJobLatitude is " + JobCollection.currentJobLatitude);
                Log.d(TAG, "JobCollection.currentJobLongitude is " + JobCollection.currentJobLongitude);
                Intent intent = new Intent(getActivity(), JobMapActivity.class);
                startActivity(intent);
            }
        });
        mVolunteerView = (TextView) v.findViewById(R.id.job_volunteer);
        Log.d(TAG, "volunteer is " + mJob.getVolunteer());
        if (!mJob.getVolunteer().isEmpty()) {
            String userId = mJob.getVolunteer();
            User volunteer = UserCollection.get(getContext()).getUser(UUID.fromString(userId));
            mVolunteerView.setText(volunteer.getFirstName() + " " + volunteer.getLastName());
        }


        mVolunteerButton = (Button) v.findViewById(R.id.volunteer_button);
        mVolunteerButton.setEnabled(true);
        mVolunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Volunteer button clicked");
                JobCollection.currentJobLatitude = mJob.getLatitude();
                JobCollection.currentJobLongitude = mJob.getLongitude();

                String posterId = mJob.getPoster().toString();
                String where = "UUID = ?";
                String[] whereArgs= new String[1];
                whereArgs[0] = posterId;
                UserCursorWrapper c = UserCollection.get(getContext()).queryJobs(where, whereArgs);
                c.moveToFirst();
                User postedBy = c.getUser();

                JobCollection.jobPosterName = postedBy.getFirstName() + " " + postedBy.getLastName();
                JobCollection.jobPosterPhone = postedBy.getPhone();
                JobCollection.jobPosterEmail = postedBy.getEmail();

                Log.d(TAG, "mJob.getPosterPhone() is " + postedBy.getPhone());
                Log.d(TAG, "mJob.getPosterEmail() is " + postedBy.getEmail());

                Log.d(TAG, "JobCollection.jobPosterPhone is " + JobCollection.jobPosterPhone);
                Log.d(TAG, "JobCollection.jobPosterEmail is " + JobCollection.jobPosterEmail);

                User user = UserCollection.get(getContext()).getCurrentUser();
                mJob.setVolunteer(user.getId().toString());
                Intent intent = new Intent(getActivity(), ThankVolunteerActivity.class);
                startActivity(intent);
            }
        });

        mVolunteerView = (TextView) v.findViewById(R.id.job_volunteer);
        Log.d(TAG, "volunteer is " + mJob.getVolunteer());
        if (!mJob.getVolunteer().isEmpty()) {
            String userId = mJob.getVolunteer();
            User volunteer = UserCollection.get(getContext()).getUser(UUID.fromString(userId));
            mVolunteerView.setText(volunteer.getFirstName() + " " + volunteer.getLastName());
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

        // Print out all users for debugging
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
