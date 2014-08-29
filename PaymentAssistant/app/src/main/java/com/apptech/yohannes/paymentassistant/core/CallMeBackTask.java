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
    private PhoneService _phoneService;
    private Contact _contact;

    public CallMeBackTask(Context context, Contact contact)
    {
        _contact = contact;
        _phoneService = new PhoneService(context);
    }

    public TaskType GetTaskType() {
        return TaskType.CallMeBack;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call("*807*" + _contact.getPhoneNumber().trim()+ Uri.encode("#"));
    }
}
