package com.spentas.javad.doctorfinder.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


/**
 * Immutable model class for a Person.
 */
public class Person {


    @SerializedName("id")
    private final int mId;

    @Nullable
    @SerializedName("name")
    private final String mName;

    @Nullable
    @SerializedName("speciality")
    private final String mSpeciality;

    @Nullable
    @SerializedName("area")
    private final String mArea;

    @Nullable
    @SerializedName("currency")
    private final String mCurrency;

    @Nullable
    @SerializedName("rate")
    private final int mRate;

    @Nullable
    @SerializedName("photo")
    private final String mPhoto;

    /**
     * Use this constructor to create a new Person.
     *
     * @param mId         the m id
     * @param mName       the m name
     * @param mSpeciality the m speciality
     * @param mArea       the m area
     * @param mCurrency   the m currency
     * @param mRate       the m rate
     * @param mPhoto      the m photo
     */
    public Person(int mId, String mName, String mSpeciality, String mArea, String mCurrency, int mRate, String mPhoto) {
        this.mId = mId;
        this.mName = mName;
        this.mSpeciality = mSpeciality;
        this.mArea = mArea;
        this.mCurrency = mCurrency;
        this.mRate = mRate;
        this.mPhoto = mPhoto;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * Gets speciality.
     *
     * @return the speciality
     */
    @Nullable
    public String getSpeciality() {
        return mSpeciality;
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
     * Gets currency.
     *
     * @return the currency
     */
    @Nullable
    public String getCurrency() {
        return mCurrency;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    @Nullable
    public int getRate() {
        return mRate;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    @Nullable
    public String getmPhoto() {
        return mPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getRate() != person.getRate()) return false;
        if (getName() != null ? !getName().equals(person.getName()) : person.getName() != null)
            return false;
        if (getSpeciality() != null ? !getSpeciality().equals(person.getSpeciality()) : person.getSpeciality() != null)
            return false;
        if (getArea() != null ? !getArea().equals(person.getArea()) : person.getArea() != null)
            return false;
        if (getCurrency() != null ? !getCurrency().equals(person.getCurrency()) : person.getCurrency() != null)
            return false;
        return getmPhoto() != null ? getmPhoto().equals(person.getmPhoto()) : person.getmPhoto() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSpeciality() != null ? getSpeciality().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        result = 31 * result + getRate();
        result = 31 * result + (getmPhoto() != null ? getmPhoto().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSpeciality='" + mSpeciality + '\'' +
                ", mArea='" + mArea + '\'' +
                ", mCurrency='" + mCurrency + '\'' +
                ", mRate=" + mRate +
                ", mPhoto='" + mPhoto + '\'' +
                '}';
    }
}
