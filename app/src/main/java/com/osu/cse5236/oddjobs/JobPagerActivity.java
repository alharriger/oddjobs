package com.osu.cse5236.oddjobs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobPagerActivity extends AppCompatActivity {
    private static final String EXTRA_JOB_ID = "com.osu.cse5236.oddjobs.job_id";

    private ViewPager mViewPager;
    private List<Job> mJobs;

    public static Intent newIntent(Context packageContext, UUID jobId) {
        Intent intent = new Intent(packageContext, JobPagerActivity.class);
        intent.putExtra(EXTRA_JOB_ID, jobId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_pager);

        UUID jobId = (UUID) getIntent().getSerializableExtra(EXTRA_JOB_ID);

        mViewPager = (ViewPager) findViewById(R.id.job_view_pager);

        mJobs = JobCollection.get(this).getJobs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Job job = mJobs.get(position);
                return JobFragment.newInstance(job.getId());
            }

            @Override
            public int getCount() {
                return mJobs.size();
            }
        });
    }
}
