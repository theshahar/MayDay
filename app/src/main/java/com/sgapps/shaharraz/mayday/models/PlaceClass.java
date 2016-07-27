package com.sgapps.shaharraz.mayday.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShaharRaz on 22/06/2016.
 */
public class PlaceClass implements Parcelable {
    private double lat;
    private double lng;
    private String name;

    protected PlaceClass(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
        name = in.readString();
    }

    public static final Creator<PlaceClass> CREATOR = new Creator<PlaceClass>() {
        @Override
        public PlaceClass createFromParcel(Parcel in) {
            return new PlaceClass(in);
        }

        @Override
        public PlaceClass[] newArray(int size) {
            return new PlaceClass[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public PlaceClass(double lat, double lng, String name) {

        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(name);
    }
}
