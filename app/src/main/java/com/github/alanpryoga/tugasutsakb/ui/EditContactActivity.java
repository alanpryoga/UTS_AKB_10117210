/**
 * Tanggal Pengerjaan   : 13/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.alanpryoga.tugasutsakb.R;
import com.github.alanpryoga.tugasutsakb.data.DbHandler;
import com.github.alanpryoga.tugasutsakb.data.DbTable;
import com.github.alanpryoga.tugasutsakb.data.model.Contact;

public class EditContactActivity extends AppCompatActivity {

    private Contact contact;

    private EditText id;
    private EditText name;
    private EditText className;
    private EditText bio;
    private EditText phone;
    private EditText email;
    private EditText socmed;

    private Button edit;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.name);
        className = (EditText) findViewById(R.id.class_name);
        bio = (EditText) findViewById(R.id.bio);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        socmed = (EditText) findViewById(R.id.socmed);

        edit = (Button) findViewById(R.id.edit);
        back = (Button) findViewById(R.id.back);

        id.setEnabled(false);

        final Intent intent = getIntent();
        String contactId = intent.getStringExtra("contact_id");

        DbHandler dbHandler = new DbHandler(this);
        SQLiteDatabase database = dbHandler.getReadableDatabase();

        String query = "SELECT * FROM " +
                DbTable.CONTACT_TABLE_NAME +
                " WHERE id = ?";

        Cursor cursor = database.rawQuery(query, new String[] {contactId});

        cursor.moveToNext();

        contact = new Contact();
        contact.setId(cursor.getString(0));
        contact.setPhoto(cursor.getString(1));
        contact.setName(cursor.getString(2));
        contact.setClassName(cursor.getString(3));
        contact.setBio(cursor.getString(4));
        contact.setEmail(cursor.getString(5));
        contact.setPhone(cursor.getString(6));
        contact.setSocmed(cursor.getString(7));

        setDisplayView(contact);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
        
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContact();
            }
        });
    }

    private void editContact() {
        DbHandler dbHandler = new DbHandler(this);

        SQLiteDatabase database = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbTable.CONTACT_FIELD_NAME, name.getText().toString());
        values.put(DbTable.CONTACT_FIELD_CLASS_NAME, className.getText().toString());
        values.put(DbTable.CONTACT_FIELD_BIO, bio.getText().toString());
        values.put(DbTable.CONTACT_FIELD_PHONE, phone.getText().toString());
        values.put(DbTable.CONTACT_FIELD_EMAIL, email.getText().toString());
        values.put(DbTable.CONTACT_FIELD_SOCMED, socmed.getText().toString());

        database.update(DbTable.CONTACT_TABLE_NAME, values, DbTable.CONTACT_FIELD_ID + " = ?", new String[]{id.getText().toString()});

        Toast.makeText(this, "Berhasil mengubah data.", Toast.LENGTH_SHORT).show();
    }

    private void setDisplayView(Contact contact) {
        id.setText(contact.getId());
        name.setText(contact.getName());
        className.setText(contact.getClassName());
        bio.setText(contact.getBio());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
        socmed.setText(contact.getSocmed());
    }
}
