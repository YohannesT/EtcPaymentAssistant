package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/19/2014.
 */
public class EVDOFillTask implements ITask {
    private PhoneService _phoneService;
    private String evdoPhoneNumber, cardNumber;

    public EVDOFillTask(Context context, String evdoPhoneNumber, String cardNumber)
    {
        this.evdoPhoneNumber = evdoPhoneNumber;
        this.cardNumber = cardNumber;
        _phoneService = new PhoneService(context);
    }

    public TaskType GetTaskType() {
        return TaskType.BalanceCheck;
    }

    @Override
    public Boolean Execute() {
        String code = "903" + Uri.encode(";") + "1" + Uri.encode("#;") + evdoPhoneNumber + Uri.encode("#;") + "1" + Uri.encode(";") + cardNumber.trim() + Uri.encode("#");
        return _phoneService.Call(code);
    }
}
