package com.shop.demo.model;

import com.shop.demo.repository.CustomerMysql;
import com.shop.demo.repository.EmployeeMysql;

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        CustomerMysql customerMysql = new CustomerMysql();
        this.customerID = customerMysql.getCustomerByID(customerID);
    }

    public Employee getServedBy() {
        return servedBy;
    }

    public void setServedBy(int servedBy) {
        EmployeeMysql employeeMysql = new EmployeeMysql();
        this.servedBy = employeeMysql.getEmployeeByID(servedBy);
    }
}
