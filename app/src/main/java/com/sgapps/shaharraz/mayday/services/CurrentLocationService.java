package com.sgapps.shaharraz.mayday.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.sgapps.shaharraz.mayday.activities.MainActivity;
import com.sgapps.shaharraz.mayday.database.MayDayDbHandler;
import com.sgapps.shaharraz.mayday.models.AddressClass;
import com.sgapps.shaharraz.mayday.models.CountryClass;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
@SuppressWarnings("ResourceType")
public class CurrentLocationService extends IntentService implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private MayDayDbHandler mHandler = new MayDayDbHandler(this);
    private String countryCode;

    public static final String ACTION_GET_DATA = "com.sgapps.shaharraz.mayday.services.ACTION_GET_DATA";

    public CurrentLocationService() {

        super("CurrentLocationService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mGoogleApiClient.connect();


    }

    @Override
    public void onConnected(Bundle bundle) {
        @SuppressWarnings("ResourceType") Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            handleNewLocation(location);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    private void handleNewLocation(Location location) {
        List<Address> addresses = null;
        AddressClass addressClass = null;
        Log.i("Location", String.valueOf(location.getLatitude()) + ", " + String.valueOf(location.getLongitude()));
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(MainActivity.LAT_SHARED_KEY, String.valueOf(location.getLatitude()));
        editor.putString(MainActivity.LONG_SHARED_KEY, String.valueOf(location.getLongitude()));
        editor.apply();

        //Get the Country Code - English Only
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);

        try

        {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                countryCode = addresses.get(0).getCountryCode();
                Log.i("Location EN", countryCode);


            }
        } catch (
                IOException e
                )

        {
            Log.e("Location", e.getMessage());
            e.printStackTrace();
        }

        Geocoder geocoderFull = new Geocoder(getApplicationContext(), Locale.getDefault());
        try

        {

            addresses = geocoderFull.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String country = addresses.get(0).getCountryName();
                Log.i("Location", address + ", " + city + ", " + country);
                addressClass = new AddressClass(address, city, country);
            }

        } catch (
                IOException e
                )

        {
            Log.e("Location", e.getMessage());
            e.printStackTrace();
        }


        CountryClass mCountryClass = mHandler.getCountryByCode(countryCode);
        Intent in = new Intent(ACTION_GET_DATA);
        if (addressClass != null) {
            in.putExtra(MainActivity.ADDRESS_KEY, addressClass);
        }

        in.putExtra(MainActivity.COUNTRY_KEY, mCountryClass);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(in);
    }
}
