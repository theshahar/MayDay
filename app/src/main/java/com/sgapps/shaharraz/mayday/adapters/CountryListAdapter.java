package com.sgapps.shaharraz.mayday.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.models.CountryClass;

import java.util.ArrayList;


/**
 * Created by ShaharRaz on 05/07/2016.
 */
public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyHolder> {

    private ArrayList<CountryClass> countries = new ArrayList<>();
    private Context context;
    private OnCountrySelectedListener mListener;


    public CountryListAdapter(Context context, OnCountrySelectedListener mListener) {
        this.context = context;
        this.mListener = mListener;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.country_item_layout, parent, false);
        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(CountryListAdapter.MyHolder holder, int position) {
        holder.bind(countries.get(position));

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtCountryName;
        private CountryClass country;

        public MyHolder(View itemView) {
            super(itemView);
            txtCountryName = (TextView) itemView.findViewById(R.id.txtCountryName);
            itemView.setOnClickListener(this);

        }

        public void bind(CountryClass c) {
            country = c;
            txtCountryName.setText(c.getName());
        }


        @Override
        public void onClick(View v) {
            mListener.countrySelected(country);

        }
    }

    public void setData(ArrayList<CountryClass> newCountries) {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();

    }

    public interface OnCountrySelectedListener {
        void countrySelected(CountryClass country);
    }

    public void filter(String text, ArrayList<CountryClass> queryCountries) {
        if (text.isEmpty()) {
            countries.clear();
            countries.addAll(queryCountries);
        } else {
            ArrayList<CountryClass> result = new ArrayList<>();
            text = text.toLowerCase();
            for (CountryClass item : queryCountries) {
                if (item.getName().toLowerCase().contains(text)) {
                    result.add(item);
                }
            }
            countries.clear();
            countries.addAll(result);
        }
        notifyDataSetChanged();
    }

}
