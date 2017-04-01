package com.osu.cse5236.oddjobs;

import java.util.Date;
import java.util.UUID;

public class Job {
    private UUID mId;
    private UUID mPoster;
    private String mTitle = "";
    private String mCompensation = "";
    private String mDescription = "";
    private Date mDate;
    private boolean mCompleted;
    private String mVolunteer = "";
    private String mCity = "";
    private double mLongitude = 0;
    private double mLatitude = 0;

    public Job() {
        mId = UUID.randomUUID();
        mCompensation = "";
    }

    public Job(UUID id) {
        mId = id;
        mCompensation = "";
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public UUID getPoster() {
        return mPoster;
    }

    public void setPoster(UUID poster) {
        this.mPoster = poster;
    }

    public String getCompensation() {
        return mCompensation;
    }

    public void setCompensation(String compensation) {
        this.mCompensation = compensation;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        this.mCompleted = completed;
    }

    public String getVolunteer() {
        return mVolunteer;
    }

    public void setVolunteer(String volunteer) {
        this.mVolunteer = volunteer;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }
}
