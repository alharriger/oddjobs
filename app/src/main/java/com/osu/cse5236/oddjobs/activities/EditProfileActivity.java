package com.osu.cse5236.oddjobs.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.osu.cse5236.oddjobs.R;
import com.osu.cse5236.oddjobs.User;
import com.osu.cse5236.oddjobs.UserCollection;

import java.util.UUID;

/**
 * Created by Zenith on 3/31/2017.
 */

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = "EditProfileAct RAWR";

    private User mUser;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mPhoneNumberField;
    private EditText mEmailAddressField;
    private String enteredFirstName;
    private String enteredLastName;
    private String enteredPhoneNumber;
    private String enteredEmailAddress;
    private Button mUpdateProfileButton;
    private Context mContext = this;

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);

        mFirstNameField = (EditText) findViewById(R.id.user_first_name);
        mLastNameField = (EditText) findViewById(R.id.user_last_name);
        mPhoneNumberField = (EditText) findViewById(R.id.user_phone_number);
        mEmailAddressField = (EditText) findViewById(R.id.user_email_address);

        Log.d(TAG, "UserCollection.editProfileUser = " + UserCollection.editProfileUser);
        if (UserCollection.editProfileUser != null) {
            mUser = UserCollection.get(this).getUser(UserCollection.editProfileUser);
            if (mUser != null) {
                if (mUser.getFirstName() != null) {mFirstNameField.setText(mUser.getFirstName());}
                if (mUser.getLastName() != null) {mLastNameField.setText(mUser.getLastName());}
                if (mUser.getPhone() != null) {mPhoneNumberField.setText(mUser.getPhone());}
                if (mUser.getEmail() != null) {mEmailAddressField.setText(mUser.getEmail());}
            }
        }

        mFirstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredFirstName = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mLastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredLastName = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPhoneNumberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredPhoneNumber = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mEmailAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredEmailAddress = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mUpdateProfileButton = (Button) findViewById(R.id.update_job_button);
        mUpdateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Update Profile button clicked");
                if (mUser != null) {
                    mUser.setFirstName(enteredFirstName);
                    mUser.setLastName(enteredLastName);
                    mUser.setPhone(enteredPhoneNumber);
                    mUser.setEmail(enteredEmailAddress);
                    UserCollection.get(mContext).updateUser(mUser);
                }
                finish();
            }
        });
    }

    public static Intent newIntent(Context packageContext, UUID jobId) {
        Log.d(TAG, "newIntent() called");
        Intent intent = new Intent(packageContext, EditProfileActivity.class);
        return intent;
    }
}
