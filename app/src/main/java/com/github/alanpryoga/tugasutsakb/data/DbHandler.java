/**
 * Tanggal Pengerjaan   : 11/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "tugas_uts_akb";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbTable.CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbTable.DROP_CONTACT_TABLE);

        onCreate(db);
    }
}