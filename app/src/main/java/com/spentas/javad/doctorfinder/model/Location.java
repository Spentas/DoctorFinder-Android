package com.spentas.javad.doctorfinder.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Immutable model class for a Location.
 */
public class Location {

    @Nullable
    @SerializedName("area")
    private final String mArea;

    @Nullable
    @SerializedName("city")
    private final String mCity;


    /**
     * Use this constructor to create a new Location.
     *
     * @param mArea the m area
     * @param mCity the m city
     */
    public Location(String mArea, String mCity) {
        this.mArea = mArea;
        this.mCity = mCity;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    @Nullable
    public String getArea() {
        return mArea;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    @Nullable
    public String getCity() {
        return mCity;
    }

    @Override
    public String toString() {
        return "Location{" +
                "mArea='" + mArea + '\'' +
                ", mCity='" + mCity + '\'' +
                '}';
    }
}
