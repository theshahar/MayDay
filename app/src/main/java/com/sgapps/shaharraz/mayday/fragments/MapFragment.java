package com.sgapps.shaharraz.mayday.fragments;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.activities.MainActivity;
import com.sgapps.shaharraz.mayday.models.PlaceClass;
import com.sgapps.shaharraz.mayday.services.SelectOnMapService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    public static final String TAG = MapFragment.class.getSimpleName();
    private GoogleMap mMap;
    private CheckBox policeSelect, hospitalSelect, fireSelect;
    private FloatingActionButton fab;
    private ProgressDialog dialog;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle(getResources().getString(R.string.loading));
        dialog.setIcon(R.drawable.ic_logo);
        dialog.show();

        policeSelect = (CheckBox) v.findViewById(R.id.policeSelect);
        hospitalSelect = (CheckBox) v.findViewById(R.id.hospitalSelect);
        fireSelect = (CheckBox) v.findViewById(R.id.fireSelect);

        policeSelect.setOnClickListener(this);
        hospitalSelect.setOnClickListener(this);
        fireSelect.setOnClickListener(this);

        // Connect to the LocalBroadCast to receive data from the SelectOnMapService
        GetSelectSpinnerService receiver = new GetSelectSpinnerService();
        IntentFilter filter = new IntentFilter(SelectOnMapService.ACTION_GET_SELECT_STATIONS);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, filter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.map);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        setMyLoc();
        dialog.dismiss();


    }

    @Override
    public void onClick(View v) {
        //Check the boxes bring the data
        //Un Check - clear the map and bring only the checked boxes
        dialog = new ProgressDialog(getContext());
        dialog.setTitle(getResources().getString(R.string.loading));
        dialog.setIcon(R.drawable.ic_logo);
        dialog.show();

        if (((CheckBox) v).isChecked()) {
            switch (v.getId()) {
                case R.id.policeSelect:
                    onChangeCheck("police");
                    break;

                case R.id.hospitalSelect:
                    onChangeCheck("hospital");
                    break;

                case R.id.fireSelect:
                    onChangeCheck("fire_station");
                    break;
            }

        } else {
            switch (v.getId()) {
                case R.id.policeSelect:
                    mMap.clear();
                    setMyLoc();
                    if (hospitalSelect.isChecked()) {
                        onChangeCheck("hospital");
                    }
                    if (fireSelect.isChecked()) {
                        onChangeCheck("fire_station");
                    }
                    break;

                case R.id.hospitalSelect:
                    mMap.clear();
                    setMyLoc();
                    if (policeSelect.isChecked()) {
                        onChangeCheck("police");
                    }
                    if (fireSelect.isChecked()) {
                        onChangeCheck("fire_station");
                    }
                    break;

                case R.id.fireSelect:
                    mMap.clear();
                    setMyLoc();
                    if (hospitalSelect.isChecked()) {
                        onChangeCheck("hospital");
                    }
                    if (policeSelect.isChecked()) {
                        onChangeCheck("fire_station");
                    }
                    break;

                default:
                    dialog.dismiss();
                    break;

            }
        }


    }

    private void setMyLoc() {
        //Get the current Location from the SharedPreferences
        //Show it on map
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        double lat = Double.parseDouble(sp.getString(MainActivity.LAT_SHARED_KEY, "1"));
        double lng = Double.parseDouble(sp.getString(MainActivity.LONG_SHARED_KEY, "1"));
        LatLng currentLatLng = new LatLng(lat, lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
        mMap.addMarker(new MarkerOptions().position(currentLatLng).title(getResources().getString(R.string.i_am_here))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        dialog.dismiss();


    }

    private void onChangeCheck(String select) {
        //Check the boxes on the MapFragment implements the SelectOnMapService
        Intent in = new Intent(getContext(), SelectOnMapService.class);
        in.putExtra(MainActivity.SELECT_ON_MAP, select);
        getContext().startService(in);


    }

    //*******************Receive the BroadCast from the Service**************************************//
    public class GetSelectSpinnerService extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String select = intent.getStringExtra(MainActivity.SELECT_ON_MAP);
            ArrayList<PlaceClass> places = new ArrayList<>();
            places = intent.getParcelableArrayListExtra(MainActivity.PLACES_SERVICE_INTENT);
            for (int i = 0; i < places.size(); i++) {
                double lat = places.get(i).getLat();
                double lng = places.get(i).getLng();
                String name = places.get(i).getName();
                LatLng latLng = new LatLng(lat, lng);
                if (select.equals("police")) {
                    mMap.addMarker(new MarkerOptions().position(latLng).title(name)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                } else {
                    if (select.equals("hospital")) {
                        mMap.addMarker(new MarkerOptions().position(latLng).title(name)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    } else {
                        mMap.addMarker(new MarkerOptions().position(latLng).title(name)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    }
                }

            }
            dialog.dismiss();

        }

    }
}
