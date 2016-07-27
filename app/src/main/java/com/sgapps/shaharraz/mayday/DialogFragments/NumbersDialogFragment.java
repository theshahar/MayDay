package com.sgapps.shaharraz.mayday.DialogFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.activities.MainActivity;
import com.sgapps.shaharraz.mayday.models.CountryClass;

/**
 * Created by ShaharRaz on 06/07/2016.
 */
public class NumbersDialogFragment extends DialogFragment {

    public static final String TAG = NumbersDialogFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.number_dialog, container, false);
        TextView txtName = (TextView) v.findViewById(R.id.txtName);
        TextView txtPolice = (TextView) v.findViewById(R.id.txtPolice);
        TextView txtAmbulance = (TextView) v.findViewById(R.id.txtAmbulance);
        TextView txtFire = (TextView) v.findViewById(R.id.txtFire);

        Bundle args = getArguments();
        CountryClass mCountryClass = args.getParcelable(MainActivity.COUNTRY_KEY);
        txtName.setText(mCountryClass.getName());

        if (mCountryClass.getThreeNum() == 1) {
            txtPolice.setText(getResources().getString(R.string.police) + ": " + mCountryClass.getPoliceNum());
            txtAmbulance.setText(getResources().getString(R.string.ambulance) + ": " + mCountryClass.getAmbulanceNum());
            txtFire.setText(getResources().getString(R.string.fire_dep) + ": " + mCountryClass.getFireNum());
        } else {
            txtPolice.setText(getResources().getString(R.string.emergency) + ": " + mCountryClass.getPoliceNum());
        }

        return v;
    }
}
