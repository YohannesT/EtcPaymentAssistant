package com.apptech.yohannes.paymentassistant.core;

import com.apptech.yohannes.paymentassistant.domain.TaskType;

/**
 * Created by Yohannes on 7/19/2014.
 */
public interface ITask {
    TaskType GetTaskType();
    Boolean Execute();
}
