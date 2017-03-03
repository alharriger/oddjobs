package com.osu.cse5236.oddjobs;

import android.provider.ContactsContract;

/**
 * Created by zenit on 3/3/2017.
 */

public class User {
    private String mFirstName;
    private String mLastName;
    private String mEmail; // TODO: find a standard type for email addresses
    private String mPhone; // TODO: find a standard type or better type for phone numbers

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public User() {

    }
}
