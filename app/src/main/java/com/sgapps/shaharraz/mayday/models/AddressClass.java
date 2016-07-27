package com.sgapps.shaharraz.mayday.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShaharRaz on 25/07/2016.
 */
public class AddressClass implements Parcelable {

    private String address;
    private String city;
    private String country;

    public AddressClass(String address, String city, String country) {

        this.address = address;
        this.city = city;
        this.country = country;
    }

    protected AddressClass(Parcel in) {
        address = in.readString();
        city = in.readString();
        country = in.readString();
    }

    public static final Creator<AddressClass> CREATOR = new Creator<AddressClass>() {
        @Override
        public AddressClass createFromParcel(Parcel in) {
            return new AddressClass(in);
        }

        @Override
        public AddressClass[] newArray(int size) {
            return new AddressClass[size];
        }
    };

    public String getAddress() {

        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(country);
    }
}
