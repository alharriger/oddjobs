package com.osu.cse5236.oddjobs;

import java.util.Date;
import java.util.UUID;

public class Job {
    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mPaidHourly;
    private Float mCompensation;
    private String mPoster;
    private String mVolunteer;

    public Job(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public Job() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
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

    public boolean isPaidHourly() {
        return mPaidHourly;
    }

    public void setPaidHourly(boolean paidHourly) {
        this.mPaidHourly = paidHourly;
    }

    public Float getCompensation() {
        return mCompensation;
    }

    public void setCompensation(Float compensation) {
        this.mCompensation = compensation;
    }

    public String getVolunteer() {
        return mVolunteer;
    }

    public void setVolunteer(String volunteer) {
        this.mVolunteer = volunteer;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        this.mPoster = poster;
    }

    public UUID getId() {
        return mId;
    }

}
