package com.osu.cse5236.oddjobs;

import android.support.v4.app.Fragment;

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new JobListFragment();
    }
}
