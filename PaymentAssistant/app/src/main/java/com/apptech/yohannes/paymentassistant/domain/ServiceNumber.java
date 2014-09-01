package com.apptech.yohannes.paymentassistant.domain;

/**
 * Created by Yohannes on 8/30/2014.
 */
public class ServiceNumber {
    public ServiceNumber(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    private String name;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
