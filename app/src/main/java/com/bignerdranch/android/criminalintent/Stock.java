package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by nbens_000 on 2/23/2016.
 */
public class Stock {

    private UUID mId;
    private String mTitle;
    private boolean mSolved;
    private String mSuspect;
    private boolean mOverWeight;
    private boolean mUnderWeight;
    private boolean mNeutral;
    private int mWeight;


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
        if (neutral) {
            this.setOverWeight(false);
            this.setUnderWeight(false);
        }
    }

    public int getWeight(){
        if (this.isOverWeight()){
            mWeight = 0;
        }else if (this.isNeutral()){
            mWeight = 1;
        }else if (this.isUnderWeight()){
            mWeight=2;
        }
        return mWeight;
    }

    public void setWeight(int weight){
        if (weight == 0){
            this.setOverWeight(true);
        }else if (weight == 1){
            this.setNeutral(true);
        }else if (weight==2){
            this.setUnderWeight(true);
        }
        mWeight = weight;
    }

    public boolean isUnderWeight(){
        return mUnderWeight;
    }

    public void setUnderWeight(boolean UW){
        mUnderWeight = UW;
        if (UW) {
            this.setOverWeight(false);
            this.setNeutral(false);
        }
    }

    public boolean isOverWeight(){
        return mOverWeight;
    }

    public void setOverWeight(boolean OW){
        mOverWeight = OW;
        if (OW){
            this.setNeutral(false);
            this.setUnderWeight(false);
        }
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

    public Stock(){
        // Generate unique modifier
        this(UUID.randomUUID());
    }

    public Stock(UUID id) {
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
