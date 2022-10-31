package com.shop.demo.model;

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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public Supplier getSupplier() {
        return supplierID;
    }

    public void setSupplier(Supplier supplierID) {
        this.supplierID = supplierID;
    }

    public Employee getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(Employee placedBy) {
        this.placedBy = placedBy;
    }
}
