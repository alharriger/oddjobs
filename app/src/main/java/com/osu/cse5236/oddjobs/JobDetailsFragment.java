package com.osu.cse5236.oddjobs;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        mViewMapButton = (Button) v.findViewById(R.id.view_map_button);
        mViewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "View map button clicked");
                Double latitude = mJob.getLatitude();
                Double longitude = mJob.getLongitude();
                Log.d(TAG, "mJob.getLatitude() is " + latitude);
                Log.d(TAG, "mJob.getLongitude() is " + longitude);

                Double ERROR = 0.001;
                if (((longitude > ERROR) || (longitude < -ERROR)) && ((latitude > ERROR) || (latitude < -ERROR))) {
                    JobCollection.currentJobLatitude = latitude;
                    JobCollection.currentJobLongitude = longitude;
                    Intent intent = new Intent(getActivity(), JobMapActivity.class);
                    startActivity(intent);
                } else {
                    alertbox("Ack", "Sorry, the poster did not have location services on when " +
                             "creating this. Please contact them for the location. \n\n" +
                             mJob.getPoster() + "\n" + mJob.getPosterPhone() + "\n" +
                             mJob.getPosterEmail());
                }
                Log.d(TAG, "mJob.getLatitude() is " + mJob.getLatitude());
                Log.d(TAG, "mJob.getLongitude() is " + mJob.getLongitude());
                Log.d(TAG, "JobCollection.currentJobLatitude is " + JobCollection.currentJobLatitude);
                Log.d(TAG, "JobCollection.currentJobLongitude is " + JobCollection.currentJobLongitude);
            }
        });

        mVolunteerButton = (Button) v.findViewById(R.id.volunteer_button);
        mVolunteerButton.setEnabled(true);
        mVolunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Volunteer button clicked");
                JobCollection.currentJobLatitude = mJob.getLatitude();
                JobCollection.currentJobLongitude = mJob.getLongitude();
                JobCollection.jobPosterName = mJob.getPoster();
                JobCollection.jobPosterPhone = mJob.getPosterPhone();
                JobCollection.jobPosterEmail = mJob.getPosterEmail();

                Log.d(TAG, "mJob.getPosterPhone() is " + mJob.getPosterPhone());
                Log.d(TAG, "mJob.getPosterEmail() is " + mJob.getPosterEmail());

                Log.d(TAG, "JobCollection.jobPosterPhone is " + JobCollection.jobPosterPhone);
                Log.d(TAG, "JobCollection.jobPosterEmail is " + JobCollection.jobPosterEmail);

                mJob.setVolunteer(UserCollection.mCurrentUserFullName);
                Intent intent = new Intent(getActivity(), ThankVolunteerActivity.class);
                startActivity(intent);
            }
        });

        mVolunteerView = (TextView) v.findViewById(R.id.job_volunteer);
        if (mJob.getVolunteer() != null) {
            mVolunteerView.setText(UserCollection.mCurrentUserFullName);
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

    protected void alertbox(String title, String mymessage) {
        Log.d(TAG, "alertbox_ called");
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setMessage(mymessage)
                .setCancelable(false)
                .setTitle(title)
                .setNegativeButton("Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
