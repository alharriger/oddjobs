package com.osu.cse5236.oddjobs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.UUID;

/**
 * Created by Zenith on 3/24/2017.
 */

public class JobFragment extends Fragment {

    private static final String TAG = "JobFragment";
    private static final String ARG_JOB_ID = "job_id";

    private Job mJob;

    private Button mVolunteerButton;

    public static JobFragment newInstance(UUID jobId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID, jobId);
        JobFragment fragment = new JobFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID jobId = (UUID) getArguments().getSerializable(ARG_JOB_ID);
        mJob = JobCollection.get(getActivity()).getJob(jobId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment, container, false);

//        mVolunteerButton = (Button) v.findViewById(R.id.volunteer_button); // TODO: fix
//        mVolunteerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "Volunteer button clicked)");
//                // TODO: Sign User up for job
//            }
//        });
        return v;
    }
    @Override
    public void onPause() {
        super.onPause();
        JobCollection.get(getActivity()).updateJob(mJob);
    }


}
