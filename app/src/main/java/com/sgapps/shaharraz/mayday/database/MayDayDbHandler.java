package com.sgapps.shaharraz.mayday.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sgapps.shaharraz.mayday.models.ContactClass;
import com.sgapps.shaharraz.mayday.models.CountryClass;

import java.util.ArrayList;

/**
 * Created by ShaharRaz on 19/06/2016.
 */
public class MayDayDbHandler {
    private MayDayDbHelper mHelper;

    public MayDayDbHandler(Context context) {
        mHelper = new MayDayDbHelper(context);
    }

    public ArrayList<CountryClass> getAllCountries() {
        ArrayList<CountryClass> listAllCountries = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(MayDayDbHelper.COUNTRY_TABLE, null, null, null, null, null, MayDayDbHelper.COUNTRY_NAME);
        while (c.moveToNext()) {
            String countryName = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_NAME));
            String policeNum = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_POLICE));
            String ambulanceNum = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_AMBULANCE));
            String fireNum = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_FIRE));
            int threeNum = c.getInt(c.getColumnIndex(MayDayDbHelper.COUNTRY_THREE));
            listAllCountries.add(new CountryClass(countryName, policeNum, ambulanceNum, fireNum, threeNum));
        }
        db.close();
        return listAllCountries;
    }


    public CountryClass getCountryByCode(String code) {
        CountryClass country = null;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(MayDayDbHelper.COUNTRY_TABLE, null, MayDayDbHelper.COUNTRY_CODE + "=?", new String[]{code},
                null, null, null);
        if (c.moveToFirst()) {
            String countryName = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_NAME));
            String policeNum = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_POLICE));
            String ambulanceNum = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_AMBULANCE));
            String fireNum = c.getString(c.getColumnIndex(MayDayDbHelper.COUNTRY_FIRE));
            int threeNum = c.getInt(c.getColumnIndex(MayDayDbHelper.COUNTRY_THREE));
            country = new CountryClass(countryName, policeNum, ambulanceNum, fireNum, threeNum);
        }
        db.close();
        return country;

    }

    public ContactClass getContactById(long id) {
        ContactClass contactClass = null;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + MayDayDbHelper.CONTACTS_TABLE + " where _id=" + id, null);
        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(MayDayDbHelper.CONTACTS_NAME));
            String country = c.getString(c.getColumnIndex(MayDayDbHelper.CONTACTS_COUNTRY_CODE));
            int telephone = c.getInt(c.getColumnIndex(MayDayDbHelper.CONTACTS_TELEPHONE));

            contactClass = new ContactClass(name, country, telephone);
        }
        db.close();
        return contactClass;
    }


    public ArrayList<ContactClass> getAllContacts() {
        ArrayList<ContactClass> listAllContacts = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(MayDayDbHelper.CONTACTS_TABLE, null, null, null, null, null, null);
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(MayDayDbHelper.CONTACTS_ID));
            String name = c.getString(c.getColumnIndex(MayDayDbHelper.CONTACTS_NAME));
            String countryCode = c.getString(c.getColumnIndex(MayDayDbHelper.CONTACTS_COUNTRY_CODE));
            int telephone = c.getInt(c.getColumnIndex(MayDayDbHelper.CONTACTS_TELEPHONE));
            listAllContacts.add(new ContactClass(id, name, countryCode, telephone));
        }
        db.close();
        return listAllContacts;
    }


    public void addContact(ContactClass contact) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MayDayDbHelper.CONTACTS_NAME, contact.getName());
        values.put(MayDayDbHelper.CONTACTS_COUNTRY_CODE, contact.getCountryCode());
        values.put(MayDayDbHelper.CONTACTS_TELEPHONE, contact.getTelephone());
        db.insert(MayDayDbHelper.CONTACTS_TABLE, null, values);
        db.close();
    }

    public void addToTemp(ContactClass contact) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MayDayDbHelper.TEMP_ID, contact.getId());
        values.put(MayDayDbHelper.TEMP_NAME, contact.getName());
        values.put(MayDayDbHelper.TEMP_COUNTRY_CODE, contact.getCountryCode());
        values.put(MayDayDbHelper.TEMP_TELEPHONE, contact.getTelephone());
        db.insert(MayDayDbHelper.TEMP_TABLE, null, values);
        db.close();
    }

    public void removeContact(long id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(MayDayDbHelper.CONTACTS_TABLE, MayDayDbHelper.CONTACTS_ID + "=" + id, null);
        db.close();
    }


    public void removeContactTemp(long id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(MayDayDbHelper.TEMP_TABLE, MayDayDbHelper.TEMP_ID + "=" + id, null);
        db.close();
    }

    public void removeContactToTemp(long id) {
        ContactClass contactClass = getContactById(id);
        addToTemp(contactClass);
        removeContact(id);


    }

    public ContactClass getAllTemp() {
        ContactClass contact = null;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(MayDayDbHelper.TEMP_TABLE, null, null, null, null, null, null);
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(MayDayDbHelper.TEMP_ID));
            String name = c.getString(c.getColumnIndex(MayDayDbHelper.TEMP_NAME));
            String countryCode = c.getString(c.getColumnIndex(MayDayDbHelper.TEMP_COUNTRY_CODE));
            int telephone = c.getInt(c.getColumnIndex(MayDayDbHelper.TEMP_TELEPHONE));
            contact = new ContactClass(id, name, countryCode, telephone);
        }
        db.close();
        return contact;
    }


    public void undoRemove() {
        ContactClass contact = getAllTemp();
        addContact(contact);
        removeContactTemp(contact.getId());

    }

    public void updateContact(ContactClass contact) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MayDayDbHelper.CONTACTS_NAME, contact.getName());
        values.put(MayDayDbHelper.CONTACTS_COUNTRY_CODE, contact.getCountryCode());
        values.put(MayDayDbHelper.CONTACTS_TELEPHONE, contact.getTelephone());
        db.update(MayDayDbHelper.CONTACTS_TABLE, values, MayDayDbHelper.CONTACTS_ID + "=" + contact.getId(), null);
        db.close();
    }

}
