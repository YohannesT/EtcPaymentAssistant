package com.apptech.yohannes.paymentassistant.services;

import android.content.Context;

import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.domain.ServiceNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yohannes Tamru on 8/30/2014.
 */
public class ServiceNumbersService {
    private Context context;

    public ServiceNumbersService(Context context) {
        this.context = context;
    }

    public List<ServiceNumber> GetEmergencyServiceNumbers() {
        List<ServiceNumber> sns = new ArrayList<ServiceNumber>();

        String[] serviceNumbers = context.getResources().getStringArray(R.array.emergencyNumbers);
        for (String serviceNumber : serviceNumbers) {
            String name = serviceNumber.split(",")[0].trim();
            String phoneNumber = serviceNumber.split(",")[1].trim();
            sns.add(new ServiceNumber(name, phoneNumber));
        }

        return sns;
    }

    public List<ServiceNumber> GetTeleServiceNumbers() {
        List<ServiceNumber> sns = new ArrayList<ServiceNumber>();

        String[] serviceNumbers = context.getResources().getStringArray(R.array.teleServiceNumbers);
        for (String serviceNumber : serviceNumbers) {
            String name = serviceNumber.split(",")[0].trim();
            String phoneNumber = serviceNumber.split(",")[1].trim();
            sns.add(new ServiceNumber(name, phoneNumber));
        }

        return sns;
    }
}
