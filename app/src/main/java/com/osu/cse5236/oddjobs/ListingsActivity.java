package com.osu.cse5236.oddjobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ListingsActivity extends AppCompatActivity {
    private static final String TAG = "ListingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_listings);
        Button mCreateNewJobButton = (Button) findViewById(R.id.create_new_job_button);
        mCreateNewJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Create new job button clicked)");
                Intent intent = new Intent(ListingsActivity.this, NewJobActivity.class);
                startActivity(intent);
            }
        });
    }
}