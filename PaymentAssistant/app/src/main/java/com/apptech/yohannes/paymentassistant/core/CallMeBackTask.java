package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/20/2014.
 */
public class CallMeBackTask implements ITask {
    Context _context;
    private PhoneService _phoneService;
    private TaskType _task_type;
    private Contact _contact;

    public CallMeBackTask(Context context, Contact contact)
    {
        _context = context;
        _contact = contact;
        _phoneService = new PhoneService(context);
    }

    public TaskType GetTaskType() {
        return _task_type;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call("*807*" + _contact.getPhoneNumber()+ Uri.encode("#"));
    }
}
