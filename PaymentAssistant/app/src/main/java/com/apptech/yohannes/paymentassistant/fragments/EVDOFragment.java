package com.apptech.yohannes.paymentassistant.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apptech.yohannes.paymentassistant.R;

/**
 * Created by Yohannes on 8/29/2014.
 */
public class EVDOFragment extends Fragment {
    private Button btnEvdoFill, btnEvdoCheck;
    private EditText txtEvdoPhoneNumber, txtCardNumber;

    private EventHandler eventHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        super.onCreate(bundle);
        View view = inflater.inflate(R.layout.fragment_evdo_layout, container, false);
        btnEvdoCheck = (Button)view.findViewById(R.id.btnCheckEVDOBalance);
        btnEvdoFill = (Button)view.findViewById(R.id.btnFillEvdo);
        txtEvdoPhoneNumber = (EditText)view.findViewById(R.id.txtEVDOPhoneNumber);
        txtCardNumber = (EditText)view.findViewById(R.id.txtEvdoCardNumber);

        btnEvdoFill.setOnClickListener(eventHandler);
        btnEvdoCheck.setOnClickListener(eventHandler);

        return view;
    }

    private class EventHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view == btnEvdoCheck)
            {

            }
            else if(view == btnEvdoFill)
            {

            }
        }
    }

}
