package com.example.contactstest.data;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.Currency;

public class ContactManager {

    @SuppressLint("Range")
    public static void contacts(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;

        String[] contacts_calls = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        Cursor cursor = contentResolver.query(contentUri, contacts_calls, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] phone_calls = {
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
                Cursor phoneCursor = contentResolver.query(uriPhone, phone_calls,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                if (phoneCursor != null && phoneCursor.moveToFirst()) {
                    do {

                        String phone = phoneCursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.d("FF", "contacts: Name: " + name + " Phone: " + phone);
                    } while (phoneCursor.moveToNext());
                    phoneCursor.close();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
