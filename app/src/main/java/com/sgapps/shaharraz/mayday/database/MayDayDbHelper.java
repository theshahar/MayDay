package com.sgapps.shaharraz.mayday.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ShaharRaz on 19/06/2016.
 */
public class MayDayDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "MayDayDataBase.db";
    private static final int DATABASE_VERSION = 1;

    //Country Table
    public static final String COUNTRY_TABLE = "country";
    public static final String COUNTRY_ID = "_id";
    public static final String COUNTRY_CODE = "code";
    public static final String COUNTRY_NAME = "name";
    public static final String COUNTRY_POLICE = "police";
    public static final String COUNTRY_AMBULANCE = "ambulance";
    public static final String COUNTRY_FIRE = "fire";
    public static final String COUNTRY_THREE = "three";
    private static final String CREATE_COUNTRY_TABLE = String.format("create table %1$s " +
                    "( %2$s integer primary key autoincrement, %3$s text, %4$s text, %5$s text, %6$s text, %7$s text, %8$s real)",
            COUNTRY_TABLE, COUNTRY_ID, COUNTRY_CODE, COUNTRY_NAME, COUNTRY_POLICE, COUNTRY_AMBULANCE, COUNTRY_FIRE, COUNTRY_THREE);
    private static final String DROP_COUNTRY_TABLE = "DROP TABLE IF EXISTS" + COUNTRY_TABLE;

    //Contact Table
    public static final String CONTACTS_TABLE = "contacts";
    public static final String CONTACTS_ID = "_id";
    public static final String CONTACTS_NAME = "name";
    public static final String CONTACTS_COUNTRY_CODE = "countryCode";
    public static final String CONTACTS_TELEPHONE = "telephone";
    private static final String CREATE_CONTACTS_TABLE = String.format("create table %1$s " +
                    "( %2$s integer primary key autoincrement, %3$s text, %4$s text, %5$s real)",
            CONTACTS_TABLE, CONTACTS_ID, CONTACTS_NAME, CONTACTS_COUNTRY_CODE, CONTACTS_TELEPHONE);
    private static final String DROP_CONTACTS_TABLE = "DROP TABLE IF EXISTS" + CONTACTS_TABLE;

    //Temp Table
    public static final String TEMP_TABLE = "temp";
    public static final String TEMP_ID = "_id";
    public static final String TEMP_NAME = "name";
    public static final String TEMP_COUNTRY_CODE = "countryCode";
    public static final String TEMP_TELEPHONE = "telephone";
    private static final String CREATE_TEMP_TABLE = String.format("create table %1$s " +
                    "( %2$s  int, %3$s text, %4$s text, %5$s real)",
            TEMP_TABLE, TEMP_ID, TEMP_NAME, TEMP_COUNTRY_CODE, TEMP_TELEPHONE);
    private static final String DROP_TEMP_TABLE = "DROP TABLE IF EXISTS" + TEMP_TABLE;

    private Context context;
    private CountriesInsert mCountriesInsert = new CountriesInsert();


    public MayDayDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COUNTRY_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_TEMP_TABLE);
        mCountriesInsert.setContentValues(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_COUNTRY_TABLE);
        db.execSQL(DROP_CONTACTS_TABLE);
        db.execSQL(DROP_TEMP_TABLE);
        onCreate(db);

    }
}

