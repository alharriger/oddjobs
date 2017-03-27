package com.osu.cse5236.oddjobs;

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

import java.util.UUID;

/**
 * Created by pavlecoric on 3/27/17.
 */

public class NewUserActivity extends AppCompatActivity {
    private static final String TAG = "NewUserActivity RAWR";
    private static final String EXTRA_USER_ID = "com.osu.oddjobs.user_id";

    //private Job mJob;
    private User mUser;
    //private EditText mTitleField;
    //private EditText mCompensationField;
    //private EditText mDescriptionField;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mPhoneField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    //private String enteredTitle;
    //private String enteredCompensation;
    //private String enteredDescription;
    private String enteredFirstName;
    private String enteredLastName;
    private String enteredPhone;
    private String enteredEmail;
    private String enteredPassword;
    private String enteredConfirmPassword;
    private Button mRegNewUserButton;
    private Context mContext = this;

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_user);

        //mJob = new Job();
        mUser = new User();

        mFirstNameField = (EditText) findViewById(R.id.first_name);
        mFirstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredFirstName = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mLastNameField = (EditText) findViewById(R.id.last_name);
        mLastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredLastName = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mPhoneField = (EditText) findViewById(R.id.phone);
        mPhoneField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredPhone = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mEmailField = (EditText) findViewById(R.id.email);
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredEmail = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        mPasswordField = (EditText) findViewById(R.id.password);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredPassword = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        mConfirmPasswordField = (EditText) findViewById(R.id.password);
        mConfirmPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredConfirmPassword = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        mRegNewUserButton = (Button) findViewById(R.id.reg_user_button);
        mRegNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Create New User button clicked");
                mUser.setFirstName(enteredFirstName);
                mUser.setLastName(enteredLastName);
                mUser.setPhone(enteredPhone);
                mUser.setEmail(enteredEmail);
                if (enteredPassword.equals(enteredConfirmPassword)) {
                    mUser.setPassword(enteredPassword);
                }
                else {
                    Log.d(TAG, "Passwords not Matching");
                }
                UserCollection.get(mContext).addUser(mUser);
                finish();
            }
        });

    }

    public static Intent newIntent(Context packageContext, UUID userId) {
        Log.d(TAG, "newIntent() called");
        Intent intent = new Intent(packageContext, NewUserActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }
}
