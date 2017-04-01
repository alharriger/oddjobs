package com.osu.cse5236.oddjobs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.osu.cse5236.oddjobs.JobCollection;
import com.osu.cse5236.oddjobs.R;

/**
 * Created by Zenith on 3/31/2017.
 */

public class ThankVolunteerActivity extends AppCompatActivity {
    private static final String TAG = "ThankVolunteerAct RAWR";

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate() called");

        TextView posterNameView;
        TextView posterPhoneView;
        TextView posterEmailView;
        Button okButton;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thank_volunteeer);

        posterNameView = (TextView) findViewById(R.id.job_poster_name);
        posterNameView.setText(JobCollection.jobPosterName);

        posterPhoneView = (TextView) findViewById(R.id.job_poster_phone);
        posterPhoneView.setText(JobCollection.jobPosterPhone);

        posterEmailView = (TextView) findViewById(R.id.job_poster_email);
        posterEmailView.setText(JobCollection.jobPosterEmail);

        okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "okay button clicked");
                finish();
            }
        });
    }
}
