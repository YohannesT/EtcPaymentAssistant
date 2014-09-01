package com.apptech.yohannes.paymentassistant.core;

import android.content.Context;
import android.net.Uri;

import com.apptech.yohannes.paymentassistant.domain.TaskType;
import com.apptech.yohannes.paymentassistant.services.PhoneService;

/**
 * Created by Yohannes on 7/20/2014.
 */
public class BalanceFillTask implements ITask {
    private PhoneService _phoneService;
    private String _cardNumber;

    public BalanceFillTask(Context context, String cardNumber)
    {
        _cardNumber = cardNumber;
        _phoneService = new PhoneService(context);
    }

    @Override
    public TaskType GetTaskType() {
        return TaskType.MobileRefill;
    }

    @Override
    public Boolean Execute() {
        return _phoneService.Call("*805*" + _cardNumber +  Uri.encode("#"));
    }
}
