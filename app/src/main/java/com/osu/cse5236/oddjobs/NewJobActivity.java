package com.osu.cse5236.oddjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by zenit on 3/24/2017.
 */

public class NewJobActivity extends AppCompatActivity {
    private static final String TAG = "NewJobActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_new_job);
        Button mCreateNewJobButton = (Button) findViewById(R.id.create_job_button);
        mCreateNewJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Create new job button clicked)");
                /* TODO: Create a new job and go back to listings */
            }
        });
    }
}
