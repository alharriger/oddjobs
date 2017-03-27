package com.osu.cse5236.oddjobs;

import android.provider.ContactsContract;

import com.osu.cse5236.oddjobs.Job;

import java.util.List;
import java.util.UUID;

/**
 * Created by zenit on 3/3/2017.
 */

public class User {
    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private ContactsContract.CommonDataKinds.Phone mPhone;
    private String mPassword;
    private List<Job> mPosted;
    private List<Job> mVolunteeredFor;

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

    public  ContactsContract.CommonDataKinds.Phone getPhone() {
        return mPhone;
    }

    public void setPhone( ContactsContract.CommonDataKinds.Phone phone) {
        mPhone = phone;
    }

    public String getPassword() { return mPassword; }

    public void setPassword(String password) { mPassword = password; }

    public List<Job> getPosted() {
        return mPosted;
    }

    public void setPosted(List<Job> posted) {
        mPosted = posted;
    }

    public List<Job> getVolunteeredFor() {
        return mVolunteeredFor;
    }

    public void setVolunteeredFor(List<Job> volunteeredFor) {
        mVolunteeredFor = volunteeredFor;
    }

    public UUID getId() {
        return mId;
    }

    public User() {
        mId = UUID.randomUUID();
    }
}
