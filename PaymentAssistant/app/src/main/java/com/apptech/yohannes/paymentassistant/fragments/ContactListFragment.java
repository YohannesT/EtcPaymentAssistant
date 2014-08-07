package com.apptech.yohannes.paymentassistant.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.adapters.ContactsAdapter;
import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.helpers.ColorsHelper;

import java.io.Serializable;
import java.util.List;

public class ContactListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private List<Contact> contactList;

    private OnFragmentInteractionListener mListener;

    private AbsListView mListView;

    private ListAdapter mAdapter;

    public static ContactListFragment newInstance(List<Contact> contactList) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putSerializable("Contacts", (Serializable)contactList);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            contactList = (List<Contact>)getArguments().getSerializable("Contacts");

        mAdapter = new ContactsAdapter(getActivity().getBaseContext(), contactList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactlist, container, false);

        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
           TextView label = (TextView)view.findViewById(R.id.txtLabel);
            Contact contact = (Contact) parent.getItemAtPosition(position);
            mListener.ShowContactDetail(contact, ((ColorDrawable) label.getBackground()).getColor()); //send the background color
        }
    }

    public interface OnFragmentInteractionListener {
        public void ShowContactDetail(Contact contact, int color);
    }

}
