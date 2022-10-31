package com.shop.demo.model;

public class InventoryItem {

    private int itemID;

    private Product productID;

    private SupplyOrder supplyOrderID;

    private CustomerOrderItem orderID;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Product getProduct() {
        return productID;
    }

    public void setProduct(Product productID) {
        this.productID = productID;
    }

    public SupplyOrder getSupplyOrder() {
        return supplyOrderID;
    }

    public void setSupplyOrder(SupplyOrder supplyOrderID) {
        this.supplyOrderID = supplyOrderID;
    }

    public CustomerOrderItem getCustomerOrderItem() {
        return orderID;
    }

    public void setCustomerOrderItem(CustomerOrderItem orderID) {
        this.orderID = orderID;

    }
}
