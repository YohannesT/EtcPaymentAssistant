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

    public static boolean IsValidCardNumber(String cardNumber)
    {
        if(IsValidPhoneNumber(cardNumber) && cardNumber.length() == 14)
            return true;

        return false;
    }

    public static boolean IsValidPhoneNumber(String phoneNumber)
    {
        for(char c : phoneNumber.toCharArray())
        {
            if(!Character.isDigit(c))
                return false;
        }

        return true;
    }



}
