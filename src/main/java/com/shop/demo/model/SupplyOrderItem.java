package com.shop.demo.model;

import com.shop.demo.repository.ProductMysql;
import com.shop.demo.repository.SupplyOrderMysql;

public class SupplyOrderItem {
//    private int lineNo;
    private SupplyOrder supplyOrderID;
    private int quantity;


    private int total;
    private Product productID;
    private String additionalInfo;



    public SupplyOrder getSupplyOrder() {
        return supplyOrderID;
    }

    public void setSupplyOrder(int supplyOrderID) {
        SupplyOrderMysql supplyOrderMysql = new SupplyOrderMysql();
        this.supplyOrderID = supplyOrderMysql.getSupplyOrderByID(supplyOrderID);
    }

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

    public void setProduct(int productID) {
        ProductMysql productMysql = new ProductMysql();
        this.productID = productMysql.getProductByID(productID);
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
