package com.apptech.yohannes.paymentassistant.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apptech.yohannes.paymentassistant.R;

/**
 * Created by Yohannes on 8/29/2014.
 */
public class EVDOFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        super.onCreate(bundle);
        View view = inflater.inflate(R.layout.fragment_evdo_layout, container, false);

        return view;
    }
}
