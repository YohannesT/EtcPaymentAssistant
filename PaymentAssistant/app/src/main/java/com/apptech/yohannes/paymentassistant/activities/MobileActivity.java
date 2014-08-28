package com.apptech.yohannes.paymentassistant.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.core.BalanceCheckTask;
import com.apptech.yohannes.paymentassistant.core.BalanceFillTask;
import com.apptech.yohannes.paymentassistant.core.ITask;
import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.fragments.ContactListFragment;
import com.apptech.yohannes.paymentassistant.fragments.ContactTasksFragment;
import com.apptech.yohannes.paymentassistant.services.ContactsService;

import java.util.List;

public class MobileActivity extends Activity implements ContactTasksFragment.OnFragmentInteractionListener, ContactListFragment.OnFragmentInteractionListener {
    List<Contact> contacts;

    //View elements
    private Button btnCheck, btnFill, btnOCR;
    private EditText etCardNumber;
    private ContactListFragment contactListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        btnCheck = (Button)findViewById(R.id.btnCheck);
        btnFill = (Button)findViewById(R.id.btnFillBalance);
        btnOCR = (Button)findViewById(R.id.btnOcr);
        etCardNumber = (EditText)findViewById(R.id.etPhoneNumber);

        EventHandler eventHandler = new EventHandler();
        btnCheck.setOnClickListener(eventHandler);
        btnFill.setOnClickListener(eventHandler);
        btnOCR.setOnClickListener(eventHandler);
        ContactsService contactService = new ContactsService(getApplicationContext());
        contacts = contactService.GetContacts();
        ShowContactListFragment(contacts);

        //To make sure the keyboard shows up everytime user taps the card number EditTextView
        etCardNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etCardNumber, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == -1)
            if(requestCode == 9)
                etCardNumber.setText(data.getStringExtra("detectedText"));
    }

    @Override
    public void ShowContactDetail(Contact contact, int backgroundColor) {
        ShowContactDetailFragment(contact, backgroundColor);
    }

    @Override
    public void hideFragment() {
        ShowContactListFragment(contacts);
    }

    private class EventHandler implements View.OnClickListener, View.OnKeyListener
    {
        @Override
        public void onClick(View view) {
            if(view == btnCheck)
            {
                ITask checkTask = new BalanceCheckTask(getApplicationContext());
                checkTask.Execute();
            }
            else if(view == btnFill)
            {
                ITask fillTask = new BalanceFillTask(getApplicationContext(), etCardNumber.getText().toString());
                fillTask.Execute();
            }
            else if(view == btnOCR)
            {
                Intent intent = new Intent(MobileActivity.this, CameraActivity.class);
                startActivityForResult(intent, 9);
            }
        }

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if(view == etCardNumber)
            {
                String numberString = etCardNumber.getText().toString();
                if(numberString.length() != 0 && numberString.length() % 3 == 0)
                {
                    etCardNumber.setText(etCardNumber.getText() + ", ");
                    etCardNumber.setSelection(etCardNumber.getText().length() - 1);
                }
            }
            return false;
        }
    }

    private void ShowContactListFragment(List<Contact> contactList) {
        if(contactListFragment == null)
            contactListFragment = new ContactListFragment().newInstance(contactList);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.animate_in, R.animator.animate_out);
        fragmentTransaction.replace(R.id.fragmentContainer, contactListFragment).commit();
    }

    private void ShowContactDetailFragment(Contact contact, int labelBackgroundColor) {
        ContactTasksFragment contactTasksFragment =  ContactTasksFragment.newInstance(contact, labelBackgroundColor);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.animator.animate_in, R.animator.animate_out);
        fragmentTransaction.replace(R.id.fragmentContainer, contactTasksFragment)
                .commit();
    }
}