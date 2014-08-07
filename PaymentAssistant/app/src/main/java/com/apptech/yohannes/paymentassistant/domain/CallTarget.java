package com.apptech.yohannes.paymentassistant.domain;
/**
 * Created by Yohannes on 7/19/2014.
 */

public class CallTarget {
    private String _name;
    private String _phone_number;

    public CallTarget(String _name, String _phone_number) {
        this._name = _name;
        this._phone_number = _phone_number;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    @Override
    public String toString() {
        return _name +  ": " + _phone_number;
    }
}
