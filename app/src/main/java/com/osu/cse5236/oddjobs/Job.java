package com.osu.cse5236.oddjobs;

import java.util.Date;
import java.util.UUID;

public class Job {
    private UUID mId;
    private String mTitle;
    private String mCompensation = "";
    private String mDescription;
    private Date mDate;
    private boolean mCompleted;

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

    public Job() {
        mId = UUID.randomUUID();
        mCompensation = "";
    }

    public Job(UUID id) {
        mId = id;
        mCompensation = "";
        mDate = new Date();
    }
}
