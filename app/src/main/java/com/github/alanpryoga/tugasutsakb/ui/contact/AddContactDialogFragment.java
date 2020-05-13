/**
 * Tanggal Pengerjaan   : 13/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.ui.contact;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.github.alanpryoga.tugasutsakb.R;
import com.github.alanpryoga.tugasutsakb.data.DbHandler;
import com.github.alanpryoga.tugasutsakb.data.DbTable;
import com.github.alanpryoga.tugasutsakb.data.model.Contact;

public class AddContactDialogFragment extends DialogFragment {

    private OnAddContactListener callback;

    public interface OnAddContactListener {
        void onAddContactSubmit(Contact contact);
    }

    public static AddContactDialogFragment newInstance(String title) {
        AddContactDialogFragment fragment = new AddContactDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title", title);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (OnAddContactListener) getFragmentManager().getFragments().get(0);
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement OnAddListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setTitle(title);
        builder.setView(inflater.inflate(R.layout.fragment_add_contact_dialog, null))
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText id = (EditText) getDialog().findViewById(R.id.id);
                        EditText name = (EditText) getDialog().findViewById(R.id.name);
                        EditText className = (EditText) getDialog().findViewById(R.id.class_name);
                        EditText bio = (EditText) getDialog().findViewById(R.id.bio);

                        Contact contact = new Contact();
                        contact.setId(id.getText().toString());
                        contact.setName(name.getText().toString());
                        contact.setClassName(className.getText().toString());
                        contact.setBio(bio.getText().toString());

                        callback.onAddContactSubmit(contact);
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddContactDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
