package com.shop.demo.model;

import com.shop.demo.repository.ProductMysql;
import com.shop.demo.repository.SupplyOrderMysql;

public class SupplyOrderItem {
//    private int lineNo;
    private int supplyOrderID;
    private int quantity;


    private int total;
    private int productID;
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

    public int getSupplyOrderID() {
        return supplyOrderID;
    }

    public void setSupplyOrderID(int supplyOrderID) {
        this.supplyOrderID = supplyOrderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
