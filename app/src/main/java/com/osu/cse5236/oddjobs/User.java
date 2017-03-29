package com.osu.cse5236.oddjobs;

import java.util.UUID;

/**
 * Created by zenit on 3/3/2017.
 */

public class User {
    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPhone;
    private String mPassword;

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

    /*public  ContactsContract.CommonDataKinds.Phone getPhone() {
        return mPhone;
    }*/

    /*public void setPhone( ContactsContract.CommonDataKinds.Phone phone) {
        mPhone = phone;
    }*/
    public String getPhone() { return mPhone; }

    public void setPhone(String phone) { mPhone = phone; }

    public String getPassword() { return mPassword; }

    public void setPassword(String password) { mPassword = password; }

    public UUID getId() {
        return mId;
    }

    public User() {
        mId = UUID.randomUUID();
    }
}
