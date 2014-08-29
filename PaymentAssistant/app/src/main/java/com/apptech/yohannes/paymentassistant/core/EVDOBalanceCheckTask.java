package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/19/2014.
 */
public class EVDOBalanceCheckTask implements ITask {
    private PhoneService _phoneService;
    private String evdoPhoneNumber;

    public EVDOBalanceCheckTask(Context context, String evdoPhoneNumber)
    {
        this.evdoPhoneNumber = evdoPhoneNumber;
        _phoneService = new PhoneService(context);
    }

    public TaskType GetTaskType() {
        return TaskType.BalanceCheck;
    }

    @Override
    public Boolean Execute() {
        String code = "903" + Uri.encode(";") + "2" + Uri.encode("#;") + evdoPhoneNumber + Uri.encode("#;") + "000000" + Uri.encode("#");
        return _phoneService.Call(code);
    }
}
