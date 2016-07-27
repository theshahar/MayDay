package com.sgapps.shaharraz.mayday.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShaharRaz on 04/07/2016.
 */
public class ContactClass implements Parcelable {
    private long id;
    private String name;
    private String countryCode;
    private int telephone;

    public ContactClass(long id, String name, String countryCode, int telephone) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.telephone = telephone;
    }

    public ContactClass(String name, String countryCode, int telephone) {
        this.name = name;
        this.countryCode = countryCode;
        this.telephone = telephone;
    }

    protected ContactClass(Parcel in) {
        id = in.readLong();
        name = in.readString();
        countryCode = in.readString();
        telephone = in.readInt();
    }

    public static final Creator<ContactClass> CREATOR = new Creator<ContactClass>() {
        @Override
        public ContactClass createFromParcel(Parcel in) {
            return new ContactClass(in);
        }

        @Override
        public ContactClass[] newArray(int size) {
            return new ContactClass[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }


    public int getTelephone() {
        return telephone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(countryCode);
        dest.writeInt(telephone);
    }
}
