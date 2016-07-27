package com.sgapps.shaharraz.mayday.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.adapters.ContactListAdapter;
import com.sgapps.shaharraz.mayday.database.MayDayDbHandler;
import com.sgapps.shaharraz.mayday.models.ContactClass;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListContactsFragment extends Fragment implements ContactListAdapter.OnUpdateContactListener {
    public static final String TAG = ListContactsFragment.class.getSimpleName();
    private ContactListAdapter mAdapter;
    private OnNewContactListener mListener;
    private ContactListAdapter.OnUpdateContactListener mOnUpdateContactListener;
    private View mainContainer;


    public ListContactsFragment() {

        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnNewContactListener) context;
        mOnUpdateContactListener = (ContactListAdapter.OnUpdateContactListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        mainContainer = getActivity().findViewById(R.id.main_container);
        mAdapter = new ContactListAdapter(getContext(), this);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_person_add);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.addNewContact();
            }
        });
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.your_contacts);
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
        MayDayDbHandler handler = new MayDayDbHandler(getContext());
        ArrayList<ContactClass> contacts = new ArrayList<>(handler.getAllContacts());
        //Check if the Array list is empty (equal to 0 )
        // if true --> show Alert Dialog with explanation about the use of this fragment
        //if false --> show the Array list using the adapter
        if (contacts.size() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.add_new_contact);
            builder.setMessage(R.string.contact_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            dialog.dismiss();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();


        } else {
            mAdapter.setData(contacts);
        }

    }

    @Override
    public void updateContact(ContactClass contactClass) {
        mOnUpdateContactListener.updateContact(contactClass);
    }

    public interface OnNewContactListener {
        void addNewContact();
    }
}
