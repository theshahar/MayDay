package com.sgapps.shaharraz.mayday.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.database.MayDayDbHandler;
import com.sgapps.shaharraz.mayday.models.ContactClass;

import java.util.ArrayList;

/**
 * Created by ShaharRaz on 04/07/2016.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyHolder> {
    private ArrayList<ContactClass> contacts = new ArrayList<>();
    private OnUpdateContactListener mListener;
    private Context context;

    public ContactListAdapter(Context context, OnUpdateContactListener mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.contact_item_layout, parent, false);
        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private TextView txtContactName;
        private ContactClass contact;
        private MayDayDbHandler mHandler = new MayDayDbHandler(context);


        public MyHolder(View itemView) {
            super(itemView);

            txtContactName = (TextView) itemView.findViewById(R.id.txtContactName);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }


        public void bind(ContactClass c) {
            contact = c;
            txtContactName.setText(c.getName());

        }

        @Override
        public void onClick(View v) {
            String countryCode = contact.getCountryCode();
            String telephone = String.valueOf(contact.getTelephone());
            if (!countryCode.startsWith("+")) {
                countryCode = "+" + countryCode;
            }

            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+" + countryCode + telephone));
            context.startActivity(i);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(1, 1, 1, R.string.update).setOnMenuItemClickListener(this);
            menu.add(2, 2, 2, R.string.delete).setOnMenuItemClickListener(this);


        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                //Update Contact - go to the AddContactFragment
                case 1:
                    mListener.updateContact(contact);

                    break;
                //Delete Contact - move the contact to temp table with the option to "undo" with the SnackBar
                case 2:
                    mHandler.removeContactToTemp(contact.getId());
                    contacts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Snackbar.make(itemView, R.string.contact_deleted, Snackbar.LENGTH_LONG)
                            .setAction(R.string.undo, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mHandler.undoRemove();
                                    //Refresh the ArrayList and set the new data in the adapter
                                    ArrayList<ContactClass> countriesRefresh = new ArrayList<ContactClass>(mHandler.getAllContacts());
                                    setData(countriesRefresh);

                                }
                            }).show();
                    break;
            }
            return true;
        }

    }

    public void setData(ArrayList<ContactClass> newContacts) {
        contacts.clear();
        contacts.addAll(newContacts);
        notifyDataSetChanged();

    }

    public interface OnUpdateContactListener {
        void updateContact(ContactClass contactClass);
    }
}
