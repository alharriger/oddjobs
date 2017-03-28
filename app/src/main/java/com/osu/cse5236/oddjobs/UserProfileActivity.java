package com.osu.cse5236.oddjobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Zenith on 3/27/2017.
 */

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileAct RAWR";

    private TextView mFirstNameView;
    private TextView mLastNameView;
    private TextView mPhoneView;
    private TextView mEmailView;
    private User mUser;

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        UUID userId = UserCollection.profileUser;
        Log.d(TAG, "UserId is " + userId);
        mUser = UserCollection.get(this).getUser(userId);
        mFirstNameView = (TextView) findViewById(R.id.first_name);
        if (mUser.getFirstName() != null) {
            mFirstNameView.setText(mUser.getFirstName());
        }
        mLastNameView = (TextView) findViewById(R.id.last_name);
        if (mUser.getLastName() != null) {
            mLastNameView.setText(mUser.getLastName());
        }
        mPhoneView = (TextView) findViewById(R.id.phone);
        if (mUser.getPhone() != null) {
            mPhoneView.setText(mUser.getPhone());
        }
        mEmailView = (TextView) findViewById(R.id.email);
        if (mUser.getEmail() != null) {
            mEmailView.setText(mUser.getEmail());
        }
    }
}
