package com.shop.demo.model;

import com.shop.demo.repository.CustomerOrderMysql;
import com.shop.demo.repository.ProductMysql;

public class CustomerOrderItem {

    private CustomerOrder orderID;
    private int quantity;
    private int sellingPrice;
    private String additionalInfo;
    private Product productID;

    public CustomerOrder getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        CustomerOrderMysql customerOrderMysql = new CustomerOrderMysql();
        this.orderID = customerOrderMysql.getCustomerOrderByID(orderID);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        ProductMysql productMysql = new ProductMysql();
        this.productID = productMysql.getProductByID(productID);
    }
}
