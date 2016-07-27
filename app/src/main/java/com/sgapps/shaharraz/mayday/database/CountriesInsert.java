package com.sgapps.shaharraz.mayday.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ShaharRaz on 19/06/2016.
 */
public class CountriesInsert {
    private static final int TRUE = 1;
    private static final int FALSE = 0;

    public void setContentValues(SQLiteDatabase db) {
        ContentValues values = new ContentValues();


        //Albania
        values.put(MayDayDbHelper.COUNTRY_CODE, "AL");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Albania");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 129);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 127);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 18);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Argentina
        values.put(MayDayDbHelper.COUNTRY_CODE, "AR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Argentina");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 101);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 107);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 100);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Australia
        values.put(MayDayDbHelper.COUNTRY_CODE, "AU");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Australia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 0);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Austria
        values.put(MayDayDbHelper.COUNTRY_CODE, "AT");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Austria");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 144);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 122);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Belgium
        values.put(MayDayDbHelper.COUNTRY_CODE, "BE");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Belgium");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 100);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 100);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Bolivia
        values.put(MayDayDbHelper.COUNTRY_CODE, "BO");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Bolivia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 110);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 118);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 119);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Bosnia and Herzegovina
        values.put(MayDayDbHelper.COUNTRY_CODE, "BA");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Bosnia and Herzegovina");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 122);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 124);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 123);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Brazil
        values.put(MayDayDbHelper.COUNTRY_CODE, "BR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Brazil");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 190);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 192);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 193);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Bulgaria
        values.put(MayDayDbHelper.COUNTRY_CODE, "BG");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Bulgaria");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 150);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 160);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Canada
        values.put(MayDayDbHelper.COUNTRY_CODE, "CA");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Canada");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 911);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Chile
        values.put(MayDayDbHelper.COUNTRY_CODE, "CL");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Chile");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 133);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 131);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 132);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //China
        values.put(MayDayDbHelper.COUNTRY_CODE, "CN");
        values.put(MayDayDbHelper.COUNTRY_NAME, "China");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 110);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 120);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 119);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Colombia
        values.put(MayDayDbHelper.COUNTRY_CODE, "CO");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Colombia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Costa Rica
        values.put(MayDayDbHelper.COUNTRY_CODE, "CR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Costa Rica");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 911);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Croatia
        values.put(MayDayDbHelper.COUNTRY_CODE, "HR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Croatia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 92);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 112);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 93);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Cyprus
        values.put(MayDayDbHelper.COUNTRY_CODE, "CY");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Cyprus");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Czech Republic
        values.put(MayDayDbHelper.COUNTRY_CODE, "CZ");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Czech Republic");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 155);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 150);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Denmark
        values.put(MayDayDbHelper.COUNTRY_CODE, "DK");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Denmark");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Egypt
        values.put(MayDayDbHelper.COUNTRY_CODE, "EG");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Egypt");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 122);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 123);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 180);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //El Salvador
        values.put(MayDayDbHelper.COUNTRY_CODE, "SV");
        values.put(MayDayDbHelper.COUNTRY_NAME, "El Salvador");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 911);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Estonia
        values.put(MayDayDbHelper.COUNTRY_CODE, "EE");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Estonia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Finland
        values.put(MayDayDbHelper.COUNTRY_CODE, "FI");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Finland");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //France
        values.put(MayDayDbHelper.COUNTRY_CODE, "FR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "France");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 15);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 18);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Germany
        values.put(MayDayDbHelper.COUNTRY_CODE, "DE");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Germany");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Greenland
        values.put(MayDayDbHelper.COUNTRY_CODE, "GL");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Greenland");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Greece
        values.put(MayDayDbHelper.COUNTRY_CODE, "GR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Greece");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 166);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 159);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Guatemala
        values.put(MayDayDbHelper.COUNTRY_CODE, "GT");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Guatemala");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 110);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 120);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 123);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Hong Kong
        values.put(MayDayDbHelper.COUNTRY_CODE, "HK");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Hong Kong");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 999);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Hungary
        values.put(MayDayDbHelper.COUNTRY_CODE, "HU");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Hungary");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 104);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 105);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Iceland
        values.put(MayDayDbHelper.COUNTRY_CODE, "IS");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Iceland");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //India
        values.put(MayDayDbHelper.COUNTRY_CODE, "IN");
        values.put(MayDayDbHelper.COUNTRY_NAME, "India");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 100);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 102);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 101);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Ireland
        values.put(MayDayDbHelper.COUNTRY_CODE, "IE");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Ireland");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Israel
        values.put(MayDayDbHelper.COUNTRY_CODE, "IL");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Israel");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 100);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 101);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 102);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Italy
        values.put(MayDayDbHelper.COUNTRY_CODE, "IT");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Italy");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 118);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 115);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Latvia
        values.put(MayDayDbHelper.COUNTRY_CODE, "LV");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Latvia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 3);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 1);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Macau
        values.put(MayDayDbHelper.COUNTRY_CODE, "MO");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Macau");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 999);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Macedonia
        values.put(MayDayDbHelper.COUNTRY_CODE, "MK");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Macedonia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 194);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 193);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Malta
        values.put(MayDayDbHelper.COUNTRY_CODE, "MT");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Malta");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 196);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 199);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Monaco
        values.put(MayDayDbHelper.COUNTRY_CODE, "MC");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Monaco");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 15);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 18);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Montenegro
        values.put(MayDayDbHelper.COUNTRY_CODE, "ME");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Montenegro");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 124);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 123);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Netherlands
        values.put(MayDayDbHelper.COUNTRY_CODE, "NL");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Netherlands");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);


        //New Zealand
        values.put(MayDayDbHelper.COUNTRY_CODE, "NZ");
        values.put(MayDayDbHelper.COUNTRY_NAME, "New Zealand");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 111);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Norway
        values.put(MayDayDbHelper.COUNTRY_CODE, "NO");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Norway");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 113);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 110);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Panama
        values.put(MayDayDbHelper.COUNTRY_CODE, "PA");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Panama");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 911);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Philippines
        values.put(MayDayDbHelper.COUNTRY_CODE, "PA");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Philippines");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 117);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Poland
        values.put(MayDayDbHelper.COUNTRY_CODE, "PL");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Poland");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Portugal
        values.put(MayDayDbHelper.COUNTRY_CODE, "PT");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Portugal");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Romania
        values.put(MayDayDbHelper.COUNTRY_CODE, "RO");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Romania");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 961);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 981);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Russia
        values.put(MayDayDbHelper.COUNTRY_CODE, "RU");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Russia");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //South Africa
        values.put(MayDayDbHelper.COUNTRY_CODE, "ZA");
        values.put(MayDayDbHelper.COUNTRY_NAME, "South Africa");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 10111);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 10177);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 10111);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Spain
        values.put(MayDayDbHelper.COUNTRY_CODE, "ES");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Spain");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Sri Lanka
        values.put(MayDayDbHelper.COUNTRY_CODE, "LK");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Sri Lanka");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 118);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 110);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 101);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Sweden
        values.put(MayDayDbHelper.COUNTRY_CODE, "SE");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Sweden");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Switzerland
        values.put(MayDayDbHelper.COUNTRY_CODE, "CH");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Switzerland");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 144);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 118);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Thailand
        values.put(MayDayDbHelper.COUNTRY_CODE, "TH");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Thailand");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 191);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 1669);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 199);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Turkey
        values.put(MayDayDbHelper.COUNTRY_CODE, "TR");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Turkey");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 155);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 112);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 110);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //Ukraine
        values.put(MayDayDbHelper.COUNTRY_CODE, "UA");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Ukraine");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 112);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 103);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 101);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);

        //United Kingdom
        values.put(MayDayDbHelper.COUNTRY_CODE, "GB");
        values.put(MayDayDbHelper.COUNTRY_NAME, "United Kingdom");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 999);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //United States
        values.put(MayDayDbHelper.COUNTRY_CODE, "US");
        values.put(MayDayDbHelper.COUNTRY_NAME, "United States");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 911);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Venezuela
        values.put(MayDayDbHelper.COUNTRY_CODE, "VE");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Venezuela");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 171);
        values.put(MayDayDbHelper.COUNTRY_THREE, FALSE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, MayDayDbHelper.COUNTRY_AMBULANCE, values);

        //Vietnam
        values.put(MayDayDbHelper.COUNTRY_CODE, "VN");
        values.put(MayDayDbHelper.COUNTRY_NAME, "Vietnam");
        values.put(MayDayDbHelper.COUNTRY_POLICE, 113);
        values.put(MayDayDbHelper.COUNTRY_AMBULANCE, 115);
        values.put(MayDayDbHelper.COUNTRY_FIRE, 114);
        values.put(MayDayDbHelper.COUNTRY_THREE, TRUE);
        db.insert(MayDayDbHelper.COUNTRY_TABLE, null, values);



    }

}

