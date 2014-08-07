package com.apptech.yohannes.paymentassistant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.helpers.ColorsHelper;

import java.util.List;

/**
 * Created by Yohannes on 7/23/2014.
 */

public class ContactsAdapter extends ArrayAdapter<Contact> {
    private List<Contact> contacts;
    private Context context;

    private static Pair<String, Integer> NameColor;

    public ContactsAdapter(Context context, List<Contact> contacts)
    {
        super(context, R.layout.contact_list_item, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = contacts.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_list_item, parent, false);
        TextView txtName = (TextView) rowView.findViewById(R.id.tvContactName);
        TextView txtPhone = (TextView) rowView.findViewById(R.id.tvContactPhone);
        TextView txtLabel = (TextView) rowView.findViewById(R.id.txtLabel);

        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhoneNumber());

        if(NameColor == null || !NameColor.first.equals(contact.getName().substring(0,1).toUpperCase()))
            NameColor = new Pair<String, Integer>(contact.getName().substring(0,1).toUpperCase(), ColorsHelper.GetRandomColor());

        txtLabel.setText(NameColor.first);
        txtLabel.setBackgroundColor(NameColor.second);

        txtLabel.setTextColor(Color.WHITE);
        return rowView;
    }
}
