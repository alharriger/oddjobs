package com.osu.cse5236.oddjobs;

import java.util.Currency;
import java.util.Date;
import java.util.UUID;

public class Job {
    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mHourlyPay;
    private Currency mCompensation;

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

    public boolean isHourlyPay() {
        return mHourlyPay;
    }

    public void setHourlyPay(boolean hourlyPay) {
        this.mHourlyPay = hourlyPay;
    }

    public Currency getCompensation() {
        return mCompensation;
    }

    public void setCompensation(Currency compensation) {
        this.mCompensation = compensation;
    }

    public Job() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }
}
