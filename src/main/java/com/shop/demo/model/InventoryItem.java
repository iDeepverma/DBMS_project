package com.shop.demo.model;

import com.shop.demo.repository.CustomerOrderMysql;
import com.shop.demo.repository.ProductMysql;
import com.shop.demo.repository.SupplyOrderMysql;

public class InventoryItem {

    private int itemID;

    private SupplyOrder supplyOrderID;

    private CustomerOrder orderID;

    private Product productID;


    public Product getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        ProductMysql productMysql = new ProductMysql();
        this.productID = productMysql.getProductByID(productID);
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public SupplyOrder getSupplyOrderID() {
        return supplyOrderID;
    }

    public void setSupplyOrderID(int supplyOrderID) {
        SupplyOrderMysql supplyOrderMysql = new SupplyOrderMysql();
        this.supplyOrderID = supplyOrderMysql.getSupplyOrderByID(supplyOrderID);
    }

    public CustomerOrder getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        CustomerOrderMysql customerOrderMysql = new CustomerOrderMysql();
        this.orderID = customerOrderMysql.getCustomerOrderByID(orderID);
    }





}
