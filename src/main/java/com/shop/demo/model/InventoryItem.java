package com.shop.demo.model;

import com.shop.demo.repository.CustomerOrderMysql;
import com.shop.demo.repository.ProductMysql;
import com.shop.demo.repository.SupplyOrderMysql;

public class InventoryItem {

    private int itemID;

    private int supplyOrderID;

    private int orderID;

    private int productID;




    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getSupplyOrderID() {
        return supplyOrderID;
    }

    public void setSupplyOrderID(int supplyOrderID) {
        this.supplyOrderID = supplyOrderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
