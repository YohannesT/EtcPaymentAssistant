package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.CallTarget;
import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.helpers.ServiceNumbers;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/19/2014.
 */
public class BalanceCheckTask implements ITask {
    Context _context;
    private PhoneService _phoneService;
    private TaskType _task_type;

    public BalanceCheckTask(Context context)
    {
        _context = context;
        _phoneService = new PhoneService(context);
    }

    public TaskType GetTaskType() {
        return _task_type;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call("*804" + Uri.encode("#"));
    }
}
