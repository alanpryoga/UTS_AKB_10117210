/**
 * Tanggal Pengerjaan   : 11/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.data;

public class DbTable {

    public static final String CONTACT_TABLE_NAME = "contacts";

    public static final String CONTACT_FIELD_ID = "id";
    public static final String CONTACT_FIELD_PHOTO = "photo";
    public static final String CONTACT_FIELD_NAME = "name";
    public static final String CONTACT_FIELD_CLASS_NAME = "class_name";
    public static final String CONTACT_FIELD_BIO = "bio";
    public static final String CONTACT_FIELD_PHONE = "phone";
    public static final String CONTACT_FIELD_EMAIL = "email";
    public static final String CONTACT_FIELD_SOCMED = "socmed";

    public static final String CREATE_CONTACT_TABLE = "CREATE TABLE " + CONTACT_TABLE_NAME + " (" +
            CONTACT_FIELD_ID + " TEXT PRIMARY KEY, " +
            CONTACT_FIELD_PHOTO + " BLOB, " +
            CONTACT_FIELD_NAME + " TEXT, " +
            CONTACT_FIELD_CLASS_NAME + " TEXT, " +
            CONTACT_FIELD_BIO + " TEXT, " +
            CONTACT_FIELD_PHONE + " TEXT, " +
            CONTACT_FIELD_EMAIL + " TEXT, " +
            CONTACT_FIELD_SOCMED + " TEXT " + ")";

    public static final String DROP_CONTACT_TABLE = "DROP TABLE IS EXISTS " + CONTACT_TABLE_NAME;
}
