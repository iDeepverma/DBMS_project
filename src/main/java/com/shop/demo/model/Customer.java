package com.shop.demo.model;

import java.time.LocalDateTime;

public class Customer {
    private int customerID;
    private String name;
    private int phone;
    private String email;
    private LocalDateTime DOB;

    private String address;
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDOB() {
        return DOB;
    }

    public void setDOB(LocalDateTime DOB) {
        this.DOB = DOB;
    }

}
