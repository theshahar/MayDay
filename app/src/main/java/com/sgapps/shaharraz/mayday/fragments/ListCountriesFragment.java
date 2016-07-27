package com.sgapps.shaharraz.mayday.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.sgapps.shaharraz.mayday.R;

import com.sgapps.shaharraz.mayday.adapters.CountryListAdapter;
import com.sgapps.shaharraz.mayday.database.MayDayDbHandler;
import com.sgapps.shaharraz.mayday.models.CountryClass;

import java.util.ArrayList;


public class ListCountriesFragment extends Fragment implements CountryListAdapter.OnCountrySelectedListener, SearchView.OnQueryTextListener {
    public static final String TAG = ListCountriesFragment.class.getSimpleName();
    private FloatingActionButton fab;
    private CountryListAdapter mAdapter;
    private CountryListAdapter.OnCountrySelectedListener mListener;
    private MayDayDbHandler mHandler;


    public ListCountriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (CountryListAdapter.OnCountrySelectedListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_countries, container, false);

        SearchView srcCountry = (SearchView) v.findViewById(R.id.srcCountry);
        srcCountry.clearFocus();
        srcCountry.setIconifiedByDefault(false);
        srcCountry.setOnQueryTextListener(this);

        mAdapter = new CountryListAdapter(getContext(), this);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.countriesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.list_countries);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        fab.hide();
        mHandler = new MayDayDbHandler(getContext());
        ArrayList<CountryClass> countries = new ArrayList<>(mHandler.getAllCountries());
        mAdapter.setData(countries);
    }

    @Override
    public void countrySelected(CountryClass country) {
        mListener.countrySelected(country);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.filter(query, null);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mHandler = new MayDayDbHandler(getContext());
        ArrayList<CountryClass> countries = new ArrayList<>(mHandler.getAllCountries());
        mAdapter.setData(countries);
        mAdapter.filter(newText, countries);
        return true;
    }
}
