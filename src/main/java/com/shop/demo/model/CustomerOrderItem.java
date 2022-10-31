package com.shop.demo.model;

public class CustomerOrderItem {
//    private int lineNo;
    private CustomerOrder orderID;
    private int quantity;
//    private int pricePerItem;

    private int sellingPrice;
    private String additionalInfo;
    private Product productID;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
//

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
//    public int getPricePerItem() {
//        return pricePerItem;
//    }
//
//    public void setPricePerItem(int pricePerItem) {
//        this.pricePerItem = pricePerItem;
//    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Product getProduct() {
        return productID;
    }

    public void setProduct(Product productID) {
        this.productID = productID;
    }

//    public int getLineNo() {
//        return lineNo;
//    }
//
//    public void setLineNo(int lineNo) {
//        this.lineNo = lineNo;
//    }

    public CustomerOrder getCustomerOrder() {
        return orderID;
    }

    public void setCustomerOrder(CustomerOrder orderID) {
        this.orderID = orderID;
    }
}
