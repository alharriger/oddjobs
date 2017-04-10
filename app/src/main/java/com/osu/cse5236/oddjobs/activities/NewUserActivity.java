package com.osu.cse5236.oddjobs.activities;

import android.content.Context;
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

import java.util.List;
import java.util.UUID;


/**
 * Created by pavlecoric on 3/27/17.
 */

public class NewUserActivity extends AppCompatActivity {
    private static final String TAG = "NewUserActivity RAWR";
    private static final String EXTRA_USER_ID = "com.osu.oddjobs.user_id";

    private User mUser;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mPhoneField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
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
        mPasswordField = (EditText) findViewById(R.id.Password);
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

        mConfirmPasswordField = (EditText) findViewById(R.id.confirm_password);
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
                Log.d(TAG, "User first name set to: " + enteredFirstName);
                mUser.setLastName(enteredLastName);
                Log.d(TAG, "User last name set to: " + enteredLastName);
                mUser.setPhone(enteredPhone);
                Log.d(TAG, "User phone set to: " + enteredPhone);
                mUser.setEmail(enteredEmail);
                Log.d(TAG, "User email set to: " + enteredEmail);
                if (enteredPassword != null) {
                    if (enteredPassword.equals(enteredConfirmPassword)) {
                        mUser.setPassword(LoginActivity.hashPassword(enteredPassword));
                        Log.d(TAG, "Passwords matched.");
                    } else {
                        Log.d(TAG, "Passwords did not match.");
                    }
                }
                Log.d(TAG, "User password set to: " + enteredPassword);

                // Test code
                UserCollection.get(mContext).addUser(mUser);
                UserCollection.get(mContext).setCurrentUser(mUser);
                UUID id = mUser.getId();

                User testUser = UserCollection.get(mContext).getUser(id);
                if (testUser.getPassword() != null) {
                    Log.d(TAG, "testUser's password is " + testUser.getPassword());
                } else {
                    Log.d(TAG, "testUser's password is null");
                }

                List<User> users = UserCollection.get(mContext).getUsers();
                Log.d(TAG, "Users are: " + users.toString());

                finish();
            }
        });

    }
}
