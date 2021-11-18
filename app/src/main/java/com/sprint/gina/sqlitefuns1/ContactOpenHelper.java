package com.sprint.gina.sqlitefuns1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactOpenHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "contactsDatabase.db";
    static final int DATABASE_VERSION = 1;

    static final String CONTACTS_TABLE = "tableContacts";
    static final String ID = "_id"; // by convention
    static final String NAME = "name";
    static final String PHONE_NUMBER = "phoneNumber";
    static final String IMAGE_RESOURCE_ID = "imageResourceId";

    public ContactOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // this is where we create our tables
        // it is only called one time
        // right before the first call to getWriteableDatabase()
        // we will have one table
        // we need to construct a SQL statement to
        // create our tableContacts table
        // SQL: structured query language
        String sqlCreate = "CREATE TABLE " + CONTACTS_TABLE +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE_NUMBER + " TEXT, " +
                IMAGE_RESOURCE_ID + " INTEGER)";
        Log.d(MainActivity.TAG, "onCreate: " + sqlCreate);
        // execute the statement
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contact.getName());
        contentValues.put(PHONE_NUMBER, contact.getPhoneNumber());
        contentValues.put(IMAGE_RESOURCE_ID, contact.getImageResourceId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(CONTACTS_TABLE, null, contentValues);
        // don't forget to close the database!!
        db.close();
    }

    // helper method
    public Cursor getSelectAllCursor() {
        // return a cursor for stepping through all records in our table
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CONTACTS_TABLE, new String[]{ID,
                                            NAME,
                                            PHONE_NUMBER,
                                            IMAGE_RESOURCE_ID},
                null, null, null, null, null);
        // don't close the database, the cursor needs it open
        return cursor;
    }

    public List<Contact> getSelectAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = getSelectAllCursor();
        // the cursor starts "one before" the first record
        // in case there is no first record
        while (cursor.moveToNext()) { // false when there are no more records to process
            // parse to the column data for the current cursor record
            // into a Contact object
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phoneNumber = cursor.getString(2);
            int imageResourceId = cursor.getInt(3);
            Contact contact = new Contact(id, name, phoneNumber, imageResourceId);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contact getSelectContactById(int idParam) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CONTACTS_TABLE, new String[]{ID,
                        NAME,
                        PHONE_NUMBER,
                        IMAGE_RESOURCE_ID},
                ID + "=?", new String[]{"" + idParam}, null, null, null);
        Contact contact = null;
        if (cursor.moveToNext()) { // false when there are no more records to process
            // parse to the column data for the current cursor record
            // into a Contact object
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phoneNumber = cursor.getString(2);
            int imageResourceId = cursor.getInt(3);
            contact = new Contact(id, name, phoneNumber, imageResourceId);
        }
        return contact;
    }

    public void updateContactById(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contact.getName());
        contentValues.put(PHONE_NUMBER, contact.getPhoneNumber());
        contentValues.put(IMAGE_RESOURCE_ID, contact.getImageResourceId());

        SQLiteDatabase db = getWritableDatabase();
        db.update(CONTACTS_TABLE, contentValues, ID + "=?",
                new String[]{"" + contact.getId()});
        db.close();
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CONTACTS_TABLE, null, null);
        db.close();
    }
}
