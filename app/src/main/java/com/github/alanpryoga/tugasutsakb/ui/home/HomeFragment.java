/**
 * Tanggal Pengerjaan   : 11/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.ui.home;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.alanpryoga.tugasutsakb.R;
import com.github.alanpryoga.tugasutsakb.adapter.ContactListAdapter;
import com.github.alanpryoga.tugasutsakb.data.DbHandler;
import com.github.alanpryoga.tugasutsakb.data.DbTable;
import com.github.alanpryoga.tugasutsakb.data.model.Contact;
import com.github.alanpryoga.tugasutsakb.ui.EditContactActivity;
import com.github.alanpryoga.tugasutsakb.ui.contact.AddContactDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View, AddContactDialogFragment.OnAddContactListener {

    private ListView listView;

    private FloatingActionButton fab;

    private ContactListAdapter adapter;

    private List<Contact> contactList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        refreshContactList();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.list_view);

        fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContactDialog();
            }
        });

        contactList = new ArrayList<>();

        loadContactListToView();

        adapter = new ContactListAdapter(getActivity(), contactList);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactList.get(position);

                Intent intent = new Intent(getActivity(), EditContactActivity.class);
                intent.putExtra("contact_id", contact.getId());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Ingin menghapus data ini?");
                builder.setMessage("Data yang telah terhapus tidak dapat dikembalikan lagi.");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact contact = contactList.get(position);

                        DeleteContact(contact.getId());
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                return true;
            }
        });
    }

    private void DeleteContact(String id) {
        DbHandler dbHandler = new DbHandler(getActivity());

        SQLiteDatabase database = dbHandler.getWritableDatabase();
        String query = "id=?";
        String where[] = {id};

        database.delete(DbTable.CONTACT_TABLE_NAME, query, where);

        Toast.makeText(getActivity(), "Berhasil menghapus kontak.", Toast.LENGTH_SHORT).show();

        refreshContactList();
    }

    private void loadContactListToView() {
        DbHandler dbHandler = new DbHandler(getActivity());

        SQLiteDatabase database = dbHandler.getReadableDatabase();

        String query = "SELECT * FROM " + DbTable.CONTACT_TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            Contact contact = new Contact();
            contact.setId(cursor.getString(0));
            contact.setName(cursor.getString(2));
            contact.setClassName(cursor.getString(3));
            contact.setBio(cursor.getString(4));

            contactList.add(contact);
        }
    }

    private void showAddContactDialog() {
        FragmentManager fragmentManager = getFragmentManager();

        AddContactDialogFragment addContactDialog = AddContactDialogFragment.newInstance("Tambah Kontak");
        addContactDialog.show(fragmentManager, "AddContactFragment");
    }

    @Override
    public void onAddContactSubmit(Contact contact) {
        DbHandler dbHandler = new DbHandler(getActivity());

        SQLiteDatabase database = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbTable.CONTACT_FIELD_ID, contact.getId());
        values.put(DbTable.CONTACT_FIELD_NAME, contact.getName());
        values.put(DbTable.CONTACT_FIELD_CLASS_NAME, contact.getClassName());
        values.put(DbTable.CONTACT_FIELD_BIO, contact.getBio());

        database.insert(DbTable.CONTACT_TABLE_NAME, null, values);

        Toast.makeText(getActivity(), "Berhasil menyimpan kontak baru.", Toast.LENGTH_SHORT).show();

        refreshContactList();
    }

    public void refreshContactList() {
        contactList.clear();

        loadContactListToView();

        adapter = new ContactListAdapter(getActivity(), contactList);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
