package com.apptech.yohannes.paymentassistant.fragments.service_numbers;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apptech.yohannes.paymentassistant.R;

/**
 * Created by Yohannes Tamru on 8/30/2014.
 */
public class serviceNumbersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        super.onCreate(bundle);
        View view = inflater.inflate(R.layout.fragment_service_numbers, container, false);

        return view;
    }
}
