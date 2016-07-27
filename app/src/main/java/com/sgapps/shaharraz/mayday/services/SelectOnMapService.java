package com.sgapps.shaharraz.mayday.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.sgapps.shaharraz.mayday.activities.MainActivity;
import com.sgapps.shaharraz.mayday.models.PlaceClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class SelectOnMapService extends IntentService {
    public static final String ACTION_GET_SELECT_STATIONS = "com.sgapps.shaharraz.mayday.services.ACTION_GET_SELECT_STATION";
    private static final String API_KEY = "AIzaSyBVbrVqATAM6zHnTCRE3rDZ0w3-WwSeDWM";


    public SelectOnMapService() {
        super("SelectOnMapService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ArrayList<PlaceClass> places = new ArrayList<>();
        String select = intent.getStringExtra(MainActivity.SELECT_ON_MAP);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        double lat = Double.parseDouble(sp.getString(MainActivity.LAT_SHARED_KEY, "1"));
        double lng = Double.parseDouble(sp.getString(MainActivity.LONG_SHARED_KEY, "1"));


        String urlAddress = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
                "%1$s,%2$s&radius=5000&type=%3$s&key=%4$s", lat, lng, select, API_KEY);

        HttpsURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(urlAddress);
            connection = (HttpsURLConnection) url.openConnection();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return;
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONObject root = new JSONObject(builder.toString());
            JSONArray results = root.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject obj = results.getJSONObject(i);
                String name = obj.getString("name");
                JSONObject geometry = obj.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                double latJson = location.getDouble("lat");
                double lngJson = location.getDouble("lng");
                places.add(new PlaceClass(latJson, lngJson, name));
            }

        } catch (MalformedURLException e) {
            Log.e("Map Places", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Map Places", e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("Map Places", e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("Map Places", e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        Intent in = new Intent(ACTION_GET_SELECT_STATIONS);
        in.putParcelableArrayListExtra(MainActivity.PLACES_SERVICE_INTENT, places);
        in.putExtra(MainActivity.SELECT_ON_MAP, select);
        LocalBroadcastManager.getInstance(this).sendBroadcast(in);


    }


}
