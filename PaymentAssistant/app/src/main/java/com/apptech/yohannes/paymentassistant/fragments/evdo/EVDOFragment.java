package com.apptech.yohannes.paymentassistant.fragments.evdo;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.core.EVDOBalanceCheckTask;
import com.apptech.yohannes.paymentassistant.core.EVDOFillTask;
import com.apptech.yohannes.paymentassistant.core.ITask;
import com.apptech.yohannes.paymentassistant.helpers.Util;

/**
 * Created by Yohannes on 8/29/2014.
 */

public class EVDOFragment extends Fragment {
    private Button btnEvdoFill, btnEvdoCheck;
    private EditText txtEvdoPhoneNumber, txtCardNumber;

    private SharedPreferences preference;
    private SharedPreferences.Editor preferenceEditor;

    private EventHandler eventHandler;

    private final String EVDO_PHONE_NUMBER = "EVDO_PHONE_NUMBER";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        super.onCreate(bundle);
        View view = inflater.inflate(R.layout.fragment_evdo_layout, container, false);
        btnEvdoCheck = (Button)view.findViewById(R.id.btnCheckEVDOBalance);
        btnEvdoFill = (Button)view.findViewById(R.id.btnFillEvdo);
        txtEvdoPhoneNumber = (EditText)view.findViewById(R.id.txtEVDOPhoneNumber);
        txtCardNumber = (EditText)view.findViewById(R.id.txtEvdoCardNumber);

        eventHandler = new EventHandler();
        preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preferenceEditor = preference.edit();

        txtEvdoPhoneNumber.setText(preference.getString(EVDO_PHONE_NUMBER, ""));

        btnEvdoFill.setOnClickListener(eventHandler);
        btnEvdoCheck.setOnClickListener(eventHandler);
        txtCardNumber.setOnClickListener(eventHandler);
        txtEvdoPhoneNumber.setOnClickListener(eventHandler);
        txtEvdoPhoneNumber.setOnKeyListener(eventHandler);
        return view;
    }

    private class EventHandler implements View.OnClickListener, View.OnKeyListener
    {
        private ITask task;

        @Override
        public void onClick(View view)
        {
            if(view == btnEvdoCheck)
            {
                if(!Util.IsValidPhoneNumber(txtEvdoPhoneNumber.getText().toString()))
                {
                    Toast.makeText(getActivity(), "Please put in the EVDO phone number", Toast.LENGTH_LONG).show();
                    return;
                }

                task = new EVDOBalanceCheckTask(getActivity(), txtEvdoPhoneNumber.getText().toString());
                task.Execute();
            }
            else if(view == btnEvdoFill)
            {
                if(!Util.IsValidCardNumber(txtCardNumber.getText().toString()) || !Util.IsValidPhoneNumber(txtEvdoPhoneNumber.getText().toString()))
                {
                    Toast.makeText(getActivity(), "Please put in a valid card number", Toast.LENGTH_LONG).show();
                    return;
                }

                task = new EVDOFillTask(getActivity(), txtEvdoPhoneNumber.getText().toString(), txtCardNumber.getText().toString());
                task.Execute();
            }
            else if(view == txtCardNumber || view == txtEvdoPhoneNumber)
            {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {

            preferenceEditor.putString(EVDO_PHONE_NUMBER, txtEvdoPhoneNumber.getText().toString());
            preferenceEditor.commit();
            return false;
        }
    }

}
