package com.apptech.yohannes.paymentassistant.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yohannes on 8/3/2014.
 */
public class Util {
    public static List<Integer> IntFactory()
    {
        List<Integer> integers = new ArrayList<Integer>();
        for(int i = 5; i <= 200; i+=5)
            integers.add(i);
        return integers;
    }
}
