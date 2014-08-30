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

    public CallTask(Context context)
    {
        _phoneService = new PhoneService(context);
    }

    public TaskType GetTaskType() {
        return TaskType.BalanceCheck;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call("*804" + Uri.encode("#"));
    }
}
