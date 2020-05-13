/**
 * Tanggal Pengerjaan   : 13/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.alanpryoga.tugasutsakb.R;
import com.github.alanpryoga.tugasutsakb.data.model.Contact;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {

    private List<Contact> list;

    public ContactListAdapter(Context context, List<Contact> list) {
        super(context, 0, list);

        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_contact, parent, false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView className = (TextView) convertView.findViewById(R.id.class_name);

        id.setText(contact.getId());
        name.setText(contact.getName());
        className.setText(contact.getClassName());

        return convertView;
    }
}
