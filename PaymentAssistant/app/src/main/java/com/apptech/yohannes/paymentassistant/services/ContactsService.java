package com.apptech.yohannes.paymentassistant.services;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

import com.apptech.yohannes.paymentassistant.domain.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactsService extends Activity {
    private Context context;

    public ContactsService(Context context) {
        this.context = context;
    }

    public List<Contact> GetContacts() {
        Cursor c = null;
        ContentResolver contentResolver = context.getContentResolver();

        if (contentResolver != null)
            c = contentResolver.query(ContactsContract.Contacts.CONTENT_URI
                    , null
                    , null
                    , null
                    , null);

        List<Contact> phoneNoList = new ArrayList<Contact>();

        if (c == null)
            return phoneNoList;

        String name, phone;
        Bitmap picture;
        String id;

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                Cursor pCur = context.getContentResolver()
                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                                , null
                                , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                                , new String[]{id}
                                , null);

                if (pCur == null) continue;

                int cNav = 0;
                for(int j = 0; j < pCur.getCount(); j++)
                {
                   if(!pCur.moveToNext())
                    break;
                    phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone = phone.replace('+', '\0')
                            .replace('-', '\0')
                            .replace("251", "\0");
                    Contact contact = new Contact(name.trim(), phone.trim());
                    phoneNoList.add(contact);

                    if(!pCur.moveToNext())
                        break;
                }
                /*while (pCur.moveToNext()) {

                    phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phone = phone.replace('+', '\0')
                                .replace('-', '\0')
                                .replace("251", "\0");
                    ContactModel contact = new ContactModel(name.trim(), phone.trim());
                    phoneNoList.add(contact);
                }*/
            }
            c.moveToNext();
        }

        Collections.sort(phoneNoList);
        return phoneNoList;
    }
}