package com.dolla.fragmentsrecyclerview;

import android.app.Application;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person> people = new ArrayList<>();
    ;

    @Override
    public void onCreate() {
        super.onCreate();
        addContacts();
    }

    public void addContacts() {
        try {
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            while (phones.moveToNext()) {
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                people.add(new Person(name, phoneNumber));
            }
            phones.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
