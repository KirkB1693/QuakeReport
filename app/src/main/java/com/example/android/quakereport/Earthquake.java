package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private Long mDate;
    private String mUrl;


    public Earthquake(double magnitude, String location, Long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getLocation() {
        return mLocation;
    }

    public Long getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "mMagnitude = " + mMagnitude +
                ", mLocation = " + mLocation +
                ", mDate = " + mDate +
                ", mUrl = " + mUrl +
                "}";
    }
}
