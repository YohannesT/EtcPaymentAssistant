package com.apptech.yohannes.paymentassistant.fragments.service_numbers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.services.ServiceNumbersService;

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

        elv = (ExpandableListView)view.findViewById(R.id.expandableListView);


        return view;
    }
}
