package com.example.kimichael.yamblz_forecast.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sinjvf on 23.07.2017.
 * saved city
 */

public class PlaceData implements Parcelable {
    private String name;
    private double latitude;
    private double longitude;

    public PlaceData(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    protected PlaceData(Parcel in) {
        this.name = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<PlaceData> CREATOR = new Parcelable.Creator<PlaceData>() {
        @Override
        public PlaceData createFromParcel(Parcel source) {
            return new PlaceData(source);
        }

        @Override
        public PlaceData[] newArray(int size) {
            return new PlaceData[size];
        }
    };
}
