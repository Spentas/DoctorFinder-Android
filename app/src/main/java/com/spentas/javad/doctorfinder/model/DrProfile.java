package com.spentas.javad.doctorfinder.model;

import android.support.annotation.Nullable;

import com.google.common.math.DoubleMath;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 /**
 * Immutable model class for a DrProfile.
 */
public class DrProfile extends Person {


    @Nullable
    @SerializedName("description")
    private final String mDescription;

    @Nullable
    @SerializedName("recommendation")
    private final int mRecommendation;

    @Nullable
    @SerializedName("schedule")
    private final String mSchedule;

    @Nullable
    @SerializedName("experience")
    private final int mExperience;


    @Nullable
    @SerializedName("latitude")
    private final double mLatitude;

    @Nullable
    @SerializedName("longitute")
    private final double mLongitude;

    /**
     * Use this constructor to create a new Dr Profile.
     * @param mId
     * @param mName
     * @param mSpeciality
     * @param mArea
     * @param mCurrency
     * @param mRate
     * @param mPhoto
     * @param mDescription
     * @param mRecommendation
     * @param mSchedule
     * @param mExperience
     */
    public DrProfile(int mId, String mName, String mSpeciality, String mArea, String mCurrency, int mRate, String mPhoto, String mDescription, int mRecommendation, String mSchedule, int mExperience, double mLatitude, double mLongitude) {
        super(mId, mName, mSpeciality, mArea, mCurrency, mRate, mPhoto);
        this.mDescription = mDescription;
        this.mRecommendation = mRecommendation;
        this.mSchedule = mSchedule;
        this.mExperience = mExperience;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    /**
     * get Experience
     *
     * @return the Experience
     */
    @Nullable
    public int getExperience() {
        return mExperience;
    }

    /**
     * get Schedule
     *
     * @return the Schedule
     */
    @Nullable
    public String getSchedule() {
        return mSchedule;
    }

    /**
     * get Recommendation
     *
     * @return the Recommendation
     */
    @Nullable
    public int getRecommendation() {
        return mRecommendation;
    }

    @Nullable
    public double getmLongitude() {
        return mLongitude;
    }

    @Nullable
    public double getmLatitude() {
        return mLatitude;
    }

    /**
     * get Description
     *
     * @return the Description
     */
    @Nullable
    public String getDescription() {
        return mDescription;
    }



    @Override
    public String toString() {
        return "DrProfile{" +
                "mDescription='" + mDescription + '\'' +
                ", mRecommendation=" + mRecommendation +
                ", mSchedule='" + mSchedule + '\'' +
                ", mExperience=" + mExperience +
                ", mLocation=" +
                '}';
    }
}
