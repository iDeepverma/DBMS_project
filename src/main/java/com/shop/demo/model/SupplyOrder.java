package com.shop.demo.model;

import com.shop.demo.repository.EmployeeMysql;
import com.shop.demo.repository.SupplierMysql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class SupplyOrder {
    private int orderID;
    private Date orderDate;
    private Date deliveryDate;
    private String deliveryStatus;
    private int totalAmount;
    private Supplier supplierID;
    private Employee placedBy;

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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Supplier getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierMysql supplierMysql = new SupplierMysql();
        this.supplierID = supplierMysql.getSupplierByID(supplierID);
    }

    public Employee getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(int placedBy) {
        EmployeeMysql employeeMysql = new EmployeeMysql();
        this.placedBy = employeeMysql.getEmployeeByID(placedBy);
    }
}
