package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by nbens_000 on 2/23/2016.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private boolean mSolved;
    private String mSuspect;
    private boolean mOverWeight;
    private boolean mUnderWeight;
    private boolean mNeutral;


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    private Date mDate;

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public boolean isNeutral(){
        return mNeutral;
    }

    public void setNeutral(boolean neutral){
        mNeutral = neutral;
    }

    public boolean isUnderWeight(){
        return mUnderWeight;
    }

    public void setUnderWeight(boolean UW){
        mUnderWeight = UW;
    }

    public boolean isOverWeight(){
        return mOverWeight;
    }

    public void setOverWeight(boolean OW){
        mOverWeight = OW;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect){
        mSuspect = suspect;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    public Crime(){
        // Generate unique modifier
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


}
