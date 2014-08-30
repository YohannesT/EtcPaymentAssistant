package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/19/2014.
 */
public class CallTask implements ITask {
   private PhoneService _phoneService;
   private String phoneNumber;

    public CallTask(Context context, String phoneNumber)
    {
        _phoneService = new PhoneService(context);
        this.phoneNumber = phoneNumber;
    }

    public TaskType GetTaskType() {
        return TaskType.BalanceCheck;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call(phoneNumber);
    }
}
