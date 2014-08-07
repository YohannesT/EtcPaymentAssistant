package com.apptech.yohannes.paymentassistant.helpers;

/**
 * Created by Yohannes on 7/19/2014.
 */
public class ServiceNumbers {
    private static String _balance_check = "804";
    private static String _refill = "805";
    private static String _transfer = "806";
    private static String _call_me_back = "807";

    public static String GetBalanceCheckNumber() {
        return _balance_check;
    }

    public static String GetRefillNumber() {
        return _refill;
    }

    public static String GetTransferNumber() {
        return _transfer;
    }

    public static String GetCallMeBackNumber() {
        return _call_me_back;
    }
}
