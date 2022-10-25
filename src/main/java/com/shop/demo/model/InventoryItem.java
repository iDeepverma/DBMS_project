package com.shop.demo.model;

public class InventoryItem {
    private int itemID;
    private Product product;
    private SupplyOrder supplyOrder;
    private CustomerOrderItem customerOrderItem;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SupplyOrder getSupplyOrder() {
        return supplyOrder;
    }

    public void setSupplyOrder(SupplyOrder supplyOrder) {
        this.supplyOrder = supplyOrder;
    }

    public CustomerOrderItem getCustomerOrderItem() {
        return customerOrderItem;
    }

    public void setCustomerOrderItem(CustomerOrderItem customerOrderItem) {
        this.customerOrderItem = customerOrderItem;
    }
}
