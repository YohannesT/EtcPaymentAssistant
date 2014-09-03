package com.apptech.yohannes.paymentassistant.fragments.service_numbers;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.adapters.ServiceNumbersExpandableListAdapater;
import com.apptech.yohannes.paymentassistant.domain.ServiceNumber;
import com.apptech.yohannes.paymentassistant.services.ServiceNumbersService;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Yohannes Tamru on 8/30/2014.
 */
public class ServiceNumbersFragment extends Fragment {

    private ServiceNumbersService numbersService;
    private ExpandableListView elv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        super.onCreate(bundle);
        View view = inflater.inflate(R.layout.fragment_service_numbers, container, false);
        numbersService = new ServiceNumbersService(getActivity());
        elv = (ExpandableListView)view.findViewById(R.id.expandableListView);

        HashMap<String, List<ServiceNumber>> serviceNumbers = new HashMap<String, List<ServiceNumber>>();
        serviceNumbers.put("Emergency Services", numbersService.GetEmergencyServiceNumbers());
        serviceNumbers.put("Telecom Services", numbersService.GetTeleServiceNumbers());

        ServiceNumbersExpandableListAdapater adapter = new ServiceNumbersExpandableListAdapater(getActivity(), serviceNumbers);
        elv.setAdapter(adapter);
        return view;
    }
}
