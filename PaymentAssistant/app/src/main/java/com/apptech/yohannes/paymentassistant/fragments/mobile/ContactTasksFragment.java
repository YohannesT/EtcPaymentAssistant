package com.apptech.yohannes.paymentassistant.fragments.mobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.core.CallMeBackTask;
import com.apptech.yohannes.paymentassistant.core.CashTransferTask;
import com.apptech.yohannes.paymentassistant.core.ITask;
import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.helpers.AnimatableLinearLayout;
import com.apptech.yohannes.paymentassistant.helpers.Util;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

public class ContactTasksFragment extends Fragment {
    private Contact contact;
    private Button btnCall, btnCallMeBack, btnTransferCash;
    private EventHandler eventHandler = new EventHandler();
    private TextView tvContactName, tvContactPhone, tvContactLabel;
    private AnimatableLinearLayout layout;
    private GestureDetector gestureDetector;
    private ListView cashSelector;
    private PhoneService  phoneService ;

    private LinearLayout userTaskControllerLayout;

    private OnContactDetailFragmentInteractionListener mListener;

    private int labelBackgroundColor = 0;

    public static ContactTasksFragment newInstance(Contact contact, int labelBackgroundColor) {
        ContactTasksFragment fragment = new ContactTasksFragment();
        Bundle args = new Bundle();
        args.putSerializable("Contact", contact);
        args.putInt("LabelColor", labelBackgroundColor);
        fragment.setArguments(args);

        return fragment;
    }

    public ContactTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contact = (Contact)getArguments().getSerializable("Contact");
            labelBackgroundColor = getArguments().getInt("LabelColor");
        }

        onAttachFragment(getParentFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        phoneService = new PhoneService(getActivity().getBaseContext());
        View view = inflater.inflate(R.layout.fragment_contact_task, container, false);
        layout = (AnimatableLinearLayout)view.findViewById(R.id.contactTaskLayout);
        gestureDetector = new GestureDetector(getActivity().getBaseContext(), new GestureHandler());

        btnCall = (Button)view.findViewById(R.id.btnCall);
        btnCallMeBack = (Button)view.findViewById(R.id.btnCallMeBack);
        btnTransferCash = (Button)view.findViewById(R.id.btnTransfer);
        tvContactName = (TextView)view.findViewById(R.id.txtContactName);
        tvContactPhone = (TextView) view.findViewById(R.id.txtContactPhone);
        tvContactLabel = (TextView)view.findViewById(R.id.txtContactLabel);
        cashSelector = (ListView)view.findViewById(R.id.cashList);

        userTaskControllerLayout = (LinearLayout)view.findViewById(R.id.userTaskLayouts);

        tvContactPhone.setText(contact.getPhoneNumber());
        tvContactName.setText(contact.getName());
        tvContactLabel.setText(contact.getName().substring(0,1).toUpperCase());

        tvContactLabel.setBackgroundColor(labelBackgroundColor);

        btnTransferCash.setOnClickListener(eventHandler);
        btnCall.setOnClickListener(eventHandler);
        btnCallMeBack.setOnClickListener(eventHandler);
        btnTransferCash.setOnLongClickListener(eventHandler);
        btnCallMeBack.setOnLongClickListener(eventHandler);
        layout.setOnTouchListener(eventHandler);
        cashSelector.setOnItemClickListener(eventHandler);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onAttachFragment(Fragment fragment) {
        try {
            mListener = (OnContactDetailFragmentInteractionListener) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private class EventHandler implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, AdapterView.OnItemClickListener
    {
        public void onClick(View view)
        {
            if(view == btnCall)
                phoneService.Call(contact);
            else if(view == btnCallMeBack)
                Toast.makeText(getActivity().getBaseContext(), "Tap and hold", Toast.LENGTH_SHORT).show();
            else if(view == btnTransferCash){
                cashSelector.setAdapter(new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_list_item_1, Util.IntFactory()));
                cashSelector.setVisibility(View.VISIBLE);
                userTaskControllerLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            ITask task;
            phoneService = new PhoneService(getActivity().getBaseContext());

            if(view == btnCallMeBack)
            {
               task = new CallMeBackTask(getActivity().getBaseContext(), contact);
               task.Execute();
            }

            return true;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            cashSelector.setVisibility(View.GONE);
            userTaskControllerLayout.setVisibility(View.VISIBLE);
            ITask cashTransferTask = new CashTransferTask(getActivity().getBaseContext(), contact, Float.parseFloat(adapterView.getItemAtPosition(i).toString()));
            cashTransferTask.Execute();
        }
    }

    private class GestureHandler extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onFling(MotionEvent m1, MotionEvent m2, float velocityX, float velocityY)
        {
            if(m2.getX() - m1.getX() > 40)
                mListener.ShowContactListFragment();
            return true;
        }
    }

    public interface OnContactDetailFragmentInteractionListener {
        public void ShowContactListFragment();
    }
}
