package com.osu.cse5236.oddjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobListFragment extends Fragment {
    private static final String TAG = "JobListFragment";
    private RecyclerView mJobRecyclerView;
    private JobAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                             savedInstanceState) {
        Log.d(TAG, "onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        mJobRecyclerView = (RecyclerView) view.findViewById(R.id.job_recycler_view);
        mJobRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        Log.d(TAG, "updateUI() called");
        JobCollection jobCollection = JobCollection.get(getActivity());
        List<Job> jobs = jobCollection.getJobs();
        if (mAdapter == null) {
            mAdapter = new JobAdapter(jobs);
            mJobRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setJobs(jobs);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu() called");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_job_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() called");
        switch (item.getItemId()) {
            case R.id.new_job:
                Job job = new Job();
                JobCollection.get(getActivity()).addJob(job);
                Intent intent = JobPagerActivity.newIntent(getActivity(), job.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class JobHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mCompensationTextView;
        private Job mJob;

        public JobHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_job, parent, false));
            mTitleTextView = (TextView) itemView.findViewById(R.id.job_title);
            mCompensationTextView = (TextView) itemView.findViewById(R.id.job_compensation);
        }

        public void bind(Job job) {
            mJob = job;
            mTitleTextView.setText(mJob.getTitle());
            mCompensationTextView.setText(mJob.getCompensation().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = JobPagerActivity.newIntent(getActivity(), mJob.getId());
            startActivity(intent);
        }
    }

    private class JobAdapter extends RecyclerView.Adapter<JobHolder> {
        private List<Job> mJobs;
        public JobAdapter(List<Job> jobs) {
            mJobs = jobs;
        }

        @Override
        public JobHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JobHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(JobHolder holder, int position) {
            Job job = mJobs.get(position);
            holder.bind(job);
        }

        @Override
        public int getItemCount() {
            return mJobs.size();
        }

        public void setJobs(List<Job> jobs) {
            mJobs = jobs;
        }
    }
}