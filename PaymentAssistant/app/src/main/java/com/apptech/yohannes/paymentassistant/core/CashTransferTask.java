package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.Contact;
import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/20/2014.
 */
public class CashTransferTask implements ITask {
    Context _context;
    private PhoneService _phoneService;
    private Contact _contact;
    private float _cash;

    public CashTransferTask(Context context, Contact contact, float cash)
    {
        _context = context;
        _contact = contact;
        _cash = cash;
        _phoneService = new PhoneService(context);
    }

    @Override
    public TaskType GetTaskType() {
        return TaskType.CashTransfer;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call("*806*" + _contact.getPhoneNumber()+ "*" + _cash +  Uri.encode("#"));
    }
}
