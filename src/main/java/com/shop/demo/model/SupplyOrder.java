package com.shop.demo.model;

import java.time.LocalDateTime;

public class SupplyOrder {
    private int orderID;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String deliveryStatus;
    private int totalAmount;
    private Supplier supplier;
    private Employee placedBy;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(Employee placedBy) {
        this.placedBy = placedBy;
    }
}
