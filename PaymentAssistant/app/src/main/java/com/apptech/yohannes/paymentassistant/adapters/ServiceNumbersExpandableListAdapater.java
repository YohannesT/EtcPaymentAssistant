package com.apptech.yohannes.paymentassistant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.core.CallTask;
import com.apptech.yohannes.paymentassistant.core.ITask;
import com.apptech.yohannes.paymentassistant.domain.ServiceNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yohannes Tamru on 8/30/2014.
 */

public class ServiceNumbersExpandableListAdapater extends BaseExpandableListAdapter {
    private Context context;
    private HashMap<String, List<ServiceNumber>> serviceNumbers;
    private List<String> headers;
    private LayoutInflater inflater;

    public ServiceNumbersExpandableListAdapater(Context context, HashMap<String, List<ServiceNumber>> serviceNumbers)
    {
        this.context = context;
        this.serviceNumbers = serviceNumbers;
        headers = new ArrayList<String>(serviceNumbers.keySet());
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return serviceNumbers.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return serviceNumbers.get(headers.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return serviceNumbers.get(headers.get(i));
    }

    @Override
    public Object getChild(int i, int i2) {
        return serviceNumbers.get(headers.get(i)).get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i * i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.service_number_group, null);
        TextView txtGroupTitle = (TextView) view.findViewById(R.id.txtGroupTitle);
        txtGroupTitle.setText(headers.get(i));

        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.service_number, null);
        final ServiceNumber serviceNumber = (ServiceNumber) getChild(i, i2);

        TextView txtServiceName = (TextView)view.findViewById(R.id.txtServiceName);
        TextView txtServiceNumber = (TextView)view.findViewById(R.id.txtServicePhone);
        ImageButton btnCall = (ImageButton)view.findViewById(R.id.btnCallService);

        txtServiceName.setText(serviceNumber.getName());
        txtServiceNumber.setText(serviceNumber.getPhoneNumber());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ITask task = new CallTask(context, serviceNumber.getPhoneNumber());
                task.Execute();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
