package com.osu.cse5236.oddjobs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private TextView mFirstNameView;
    private TextView mLastNameView;
    private TextView mPhoneView;
    private TextView mEmailView;
    private User mUser;

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        String email = UserCollection.profileUserEmail;

        // test code
        Log.d(TAG, "UserId is " + email);
        List<User> users = UserCollection.get(UserProfileActivity.this).getUsers();
        Log.d(TAG, "All users are: " + users);
        Log.d(TAG, "User collection:");
        for (User user : users) {
            if (user.getId() != null) {Log.d(TAG, "id: " + user.getId());}
            if (user.getFirstName() != null) {Log.d(TAG, "first name: " + user.getFirstName());}
            if (user.getLastName() != null) {Log.d(TAG, "last name: " + user.getLastName());}
            if (user.getPhone() != null) {Log.d(TAG, "phone: " + user.getPhone());}
            if (user.getEmail() != null) {Log.d(TAG, "email: " + user.getEmail());}
            if (user.getPassword() != null) {Log.d(TAG, "password: " + user.getPassword());}
            Log.d(TAG, " *********************** ");
        }

        mFirstNameView = (TextView) findViewById(R.id.user_first_name);
        mLastNameView = (TextView) findViewById(R.id.user_last_name);
        mPhoneView = (TextView) findViewById(R.id.user_phone);
        mEmailView = (TextView) findViewById(R.id.user_email);

        Log.d(TAG, "email is "+ email);
        Log.d(TAG, "size of users is: " + users.size());
        for (User u : users) {
            Log.d(TAG, "looping with user: " + u.toString());
            Log.d(TAG, "This user email is: " + u.getEmail());
            if (u.getEmail().equals(email)) {
                Log.d(TAG, "got into if statement: user.getEmail().equals(email)");
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
    }
}
