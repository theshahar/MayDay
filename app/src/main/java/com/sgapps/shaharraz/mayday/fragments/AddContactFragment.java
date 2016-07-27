package com.sgapps.shaharraz.mayday.fragments;


import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.activities.MainActivity;
import com.sgapps.shaharraz.mayday.database.MayDayDbHandler;
import com.sgapps.shaharraz.mayday.models.ContactClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = AddContactFragment.class.getSimpleName();
    private EditText txtContactName;
    private EditText txtCountryCode;
    private EditText txtTelephone;
    private ContactClass mContactClass = null;
    private boolean isUpdate = false;
    private View mainContainer;

    public AddContactFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Argument
        Bundle arg = getArguments();
        if (arg != null) {
            isUpdate = arg.getBoolean(MainActivity.IS_UPDATE_KEY);
            mContactClass = arg.getParcelable(MainActivity.CONTACT_KEY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        txtContactName = (EditText) v.findViewById(R.id.txtName);
        txtCountryCode = (EditText) v.findViewById(R.id.txtCountryCode);
        txtTelephone = (EditText) v.findViewById(R.id.txtTelephone);
        CardView card = (CardView) v.findViewById(R.id.view);
        mainContainer = getActivity().findViewById(R.id.main_container);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_new_contact);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white);
        fab.show();
        fab.setOnClickListener(this);


        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        if (isUpdate) {
            //if true --> Update Contact - fill the fields with the contact's detail
            txtContactName.setText(mContactClass.getName());
            txtCountryCode.setText(mContactClass.getCountryCode());
            txtTelephone.setText(String.valueOf(mContactClass.getTelephone()));
            toolbar.setTitle(R.string.update_contact);
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onClick(View v) {
        String first = txtContactName.getText().toString();
        String country = txtCountryCode.getText().toString();
        String telephoneString = txtTelephone.getText().toString();

        if (!TextUtils.isEmpty(first) && !TextUtils.isEmpty(country)
                && !TextUtils.isEmpty(telephoneString)) {

            int telephone = Integer.parseInt(telephoneString);
            MayDayDbHandler handler = new MayDayDbHandler(getContext());
            if (isUpdate) {
                long id = mContactClass.getId();
                ContactClass contactClass = new ContactClass(id, first, country, telephone);
                handler.updateContact(contactClass);
                Snackbar.make(mainContainer, R.string.contact_updated, Snackbar.LENGTH_LONG).show();

            } else {
                ContactClass contactClass = new ContactClass(first, country, telephone);
                handler.addContact(contactClass);
                Snackbar.make(mainContainer, R.string.new_contact_added, Snackbar.LENGTH_LONG).show();
            }

            getActivity().onBackPressed();

        } else {
            Snackbar.make(mainContainer, R.string.message_missing_fields, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }


    }


}
