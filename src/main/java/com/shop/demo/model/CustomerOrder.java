package com.shop.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomerOrder {
    private int orderID;
    private Date orderDate;
    private int transactionID;
    private String modeOfPayment;
    private int total;
    private Customer customerID;
    private Employee servedBy;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return orderDate;
    }

    public void setDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customerID;
    }

    public void setCustomer(Customer customerID) {
        this.customerID = customerID;
    }

    public Employee getEmployee() {
        return servedBy;
    }

    public void setEmployee(Employee servedBy) {
        this.servedBy = servedBy;
    }
}
