package com.apptech.yohannes.paymentassistant.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private List<Contact> contacts;
    private Fragment contactListFragment;
    private ActionBarDrawerToggle drawerToggle;

    private DrawerLayout drawerLayout;
    private ListView drawerMenu;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_navigation);

        drawerLayout = (DrawerLayout)findViewById(R.id.mainDrawer);
        drawerMenu = (ListView)findViewById(R.id.navigationDrawerList);

        ContactsService contactService = new ContactsService(getApplicationContext());
        contacts = contactService.GetContacts();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.open, R.string.close);

        String [] menuItems = getResources().getStringArray(R.array.menuItems);
        drawerMenu.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems));
        drawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedMenuItem = (String) adapterView.getItemAtPosition(i);

                if(i == 0)
                {
                    Fragment fragment = new MobileActivity();
                    getFragmentManager().beginTransaction().replace(R.id.mainContent, fragment).commit();
                    ShowContactListFragment();
                }

                else {
                    Fragment fragment = new About();
                    getFragmentManager().beginTransaction().replace(R.id.mainContent, fragment).commit();
                }

                getActionBar().setTitle(getResources().getString(R.string.app_name) + " " + selectedMenuItem);

                drawerLayout.closeDrawers();
            }
        });

        Fragment mobileActivity = new MobileActivity();
        getFragmentManager().beginTransaction().replace(R.id.mainContent, mobileActivity).commit();
        ShowContactListFragment();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        contactListFragment = ContactListFragment.newInstance(contacts);
    }

    @Override
    public void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
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
        ShowContactListFragment();
    }

    private void ShowContactListFragment() {
        if(contactListFragment == null)
            contactListFragment = ContactListFragment.newInstance(contacts);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.animate_in, R.animator.animate_out);
        fragmentTransaction.remove(contactListFragment);//this solves a problem where the contact list is sometimes not displayed
        fragmentTransaction.replace(R.id.fragmentContainer, contactListFragment).commit();
    }

}
