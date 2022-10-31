package com.shop.demo.model;

public class SupplyOrderItem {
//    private int lineNo;
    private SupplyOrder supplyOrderID;
    private int quantity;

    public SupplyOrder getSupplyOrder() {
        return supplyOrderID;
    }

    public void setSupplyOrder(SupplyOrder supplyOrderID) {
        this.supplyOrderID = supplyOrderID;
    }

    private int total;
    private Product productID;
    private String additionalInfo;



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Product getProduct() {
        return productID;
    }

    public void setProduct(Product productID) {
        this.productID = productID;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
