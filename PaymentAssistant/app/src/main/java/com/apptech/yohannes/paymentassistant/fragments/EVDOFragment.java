package com.apptech.yohannes.paymentassistant.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.core.EVDOBalanceCheckTask;
import com.apptech.yohannes.paymentassistant.core.EVDOFillTask;
import com.apptech.yohannes.paymentassistant.core.ITask;

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

        eventHandler = new EventHandler();
        
        btnEvdoFill.setOnClickListener(eventHandler);
        btnEvdoCheck.setOnClickListener(eventHandler);
        txtCardNumber.setOnClickListener(eventHandler);
        txtEvdoPhoneNumber.setOnClickListener(eventHandler);
        return view;
    }

    private class EventHandler implements View.OnClickListener
    {
        private ITask task;

        @Override
        public void onClick(View view)
        {
            if(view == btnEvdoCheck)
            {
                task = new EVDOBalanceCheckTask(getActivity(), txtEvdoPhoneNumber.getText().toString());
                task.Execute();
            }
            else if(view == btnEvdoFill)
            {
                task = new EVDOFillTask(getActivity(), txtEvdoPhoneNumber.getText().toString(), txtCardNumber.getText().toString());
                task.Execute();
            }
            else if(view == txtCardNumber || view == txtEvdoPhoneNumber)
            {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

}
