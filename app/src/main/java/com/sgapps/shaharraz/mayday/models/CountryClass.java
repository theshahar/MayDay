package com.sgapps.shaharraz.mayday.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShaharRaz on 19/06/2016.
 */
public class CountryClass implements Parcelable {


    private String code;
    private String name;
    private String policeNum;
    private String ambulanceNum;
    private String fireNum;
    private int threeNum;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPoliceNum() {
        return policeNum;
    }

    public String getAmbulanceNum() {
        return ambulanceNum;
    }

    public String getFireNum() {
        return fireNum;
    }

    public int getThreeNum() {
        return threeNum;
    }

    public CountryClass(String code,String name, String policeNum, String ambulanceNum, String fireNum, int threeNum) {

        this.code = code;
        this.name = name;
        this.policeNum = policeNum;
        this.ambulanceNum = ambulanceNum;
        this.fireNum = fireNum;
        this.threeNum = threeNum;
    }

    public CountryClass(Parcel input) {
        code = input.readString();
        name = input.readString();
        policeNum = input.readString();
        ambulanceNum = input.readString();
        fireNum = input.readString();
        threeNum = input.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public CountryClass(String name, String policeNum, String ambulanceNum, String fireNum, int threeNum) {
        this.name = name;
        this.policeNum = policeNum;
        this.ambulanceNum = ambulanceNum;
        this.fireNum = fireNum;
        this.threeNum = threeNum;
    }

    public CountryClass(String name, String policeNum, String ambulanceNum, String fireNum) {
        this.name = name;
        this.policeNum = policeNum;
        this.ambulanceNum = ambulanceNum;
        this.fireNum = fireNum;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(policeNum);
        dest.writeString(ambulanceNum);
        dest.writeString(fireNum);
        dest.writeInt(threeNum);

    }

    public static final Parcelable.Creator<CountryClass> CREATOR
            = new Parcelable.Creator<CountryClass>() {
        public CountryClass createFromParcel(Parcel in) {
            return new CountryClass(in);
        }

        public CountryClass[] newArray(int size) {
            return new CountryClass[size];
        }
    };
}
