package com.osu.cse5236.oddjobs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.osu.cse5236.oddjobs.R;
import com.osu.cse5236.oddjobs.User;
import com.osu.cse5236.oddjobs.UserCollection;

import java.util.List;

/**
 * Created by Zenith on 3/27/2017.
 */

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileAct RAWR";
    private static final String EXTRA_USER_ID = "com.osu.oddjobs.user_id";

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView mFirstNameView;
        TextView mLastNameView;
        TextView mPhoneView;
        TextView mEmailView;

        mFirstNameView = (TextView) findViewById(R.id.user_first_name);
        mLastNameView = (TextView) findViewById(R.id.user_last_name);
        mPhoneView = (TextView) findViewById(R.id.user_phone);
        mEmailView = (TextView) findViewById(R.id.user_email);

        List<User> users = UserCollection.get(UserProfileActivity.this).getUsers();
        String email = UserCollection.profileUserEmail;
        Log.d(TAG, "email is "+ email);
        Log.d(TAG, "size of users is: " + users.size());
        for (User u : users) {
            Log.d(TAG, "looping with user: " + u.toString());
            Log.d(TAG, "This user email is: " + u.getEmail());
            if (u.getEmail().equals(email)) {
                Log.d(TAG, "got into if statement: user.getEmail().equals(email)");
                UserCollection.editProfileUser = u.getId();
                if (u.getFirstName() != null) {
                    Log.d(TAG, "name is " + u.getFirstName());
                    mFirstNameView.setText(u.getFirstName());
                }
                if (u.getLastName() != null) {
                    Log.d(TAG, "name is " + u.getLastName());
                    mLastNameView.setText(u.getLastName());
                }
                if (u.getPhone() != null) {
                    Log.d(TAG, "name is " + u.getPhone());
                    mPhoneView.setText(u.getPhone());
                }
                if (u.getEmail() != null) {
                    Log.d(TAG, "name is " + u.getEmail());
                    mEmailView.setText(u.getEmail());
                }
                break;
            }
        }

        Button editProfileButton = (Button) findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Edit button clicked");
                Log.d(TAG, "UserCollection.editProfileUser = " + UserCollection.editProfileUser);
                Intent intent = EditProfileActivity.newIntent(UserProfileActivity.this,
                        UserCollection.editProfileUser);
                intent.putExtra(EXTRA_USER_ID, UserCollection.editProfileUser);
                startActivity(intent);
            }
        });
    }
}
