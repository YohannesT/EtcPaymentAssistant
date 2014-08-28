package com.apptech.yohannes.paymentassistant.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.fragments.ContactListFragment;
import com.apptech.yohannes.paymentassistant.fragments.ContactTasksFragment;
import com.apptech.yohannes.paymentassistant.services.ContactsService;

import java.util.List;

/**
 * Created by Yohannes on 8/28/2014.
 */
public class MainActivity extends Activity implements ContactListFragment.OnFragmentInteractionListener, ContactTasksFragment.OnFragmentInteractionListener {
    List<Contact> contacts;

    private FrameLayout mainContentView;
    private ListView drawerMenu;

    private Fragment contactListFragment;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.activity_main_navigation);

        mainContentView = (FrameLayout) findViewById(R.id.mainContent);
        drawerMenu = (ListView)findViewById(R.id.navigationDrawerList);

        ContactsService contactService = new ContactsService(getApplicationContext());
        contacts = contactService.GetContacts();

        String [] menuItems = getResources().getStringArray(R.array.menuItems);
        drawerMenu.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems));
        drawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedMenuItem = (String) adapterView.getItemAtPosition(i);
                Fragment fragment;
                if(i == 0)
                    fragment = new MobileActivity();
                else
                    fragment = new About();

                getFragmentManager().beginTransaction().replace(R.id.mainContent, fragment).commit();
            }
        });

        MobileActivity mobileActivity = new MobileActivity();
        getFragmentManager().beginTransaction().replace(R.id.mainContent, mobileActivity).commit();
        contactListFragment = ContactListFragment.newInstance(contacts);
        ShowContactListFragment(contacts);
    }

    @Override
    public void ShowContactDetail(Contact contact, int backgroundColor) {
        ContactTasksFragment contactTasksFragment =  ContactTasksFragment.newInstance(contact, backgroundColor);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.animator.animate_in, R.animator.animate_out);
        fragmentTransaction.replace(R.id.fragmentContainer, contactTasksFragment)
                .commit();
    }

    @Override
    public void HideContactDetail() {
        ShowContactListFragment(contacts);
    }

    private void ShowContactListFragment(List<Contact> contactList) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.animate_in, R.animator.animate_out);
        fragmentTransaction.replace(R.id.fragmentContainer, contactListFragment).commit();
    }

}
