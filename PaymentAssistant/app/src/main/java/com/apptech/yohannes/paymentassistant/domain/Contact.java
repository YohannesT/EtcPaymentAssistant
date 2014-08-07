package com.apptech.yohannes.paymentassistant.domain;

import java.io.Serializable;

public class Contact implements Comparable<Contact>, Serializable {
    private String Name;
    private String PhoneNumber;

    @Override
    public String toString() {
        return Name + " - "
                + PhoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public Contact(String name, String phoneNumber) {
        PhoneNumber = phoneNumber;
        Name = name;
    }

    @Override
    public int compareTo(Contact  contactModel) {
        return Name.trim().toUpperCase().compareTo(contactModel.getName().trim().toUpperCase());
    }
}
