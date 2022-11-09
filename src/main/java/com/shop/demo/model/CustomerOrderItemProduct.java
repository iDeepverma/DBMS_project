package com.shop.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderItemProduct {
    private Product product;
    private CustomerOrderItem item;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerOrderItem getItem() {
        return item;
    }

    public void setItem(CustomerOrderItem item) {
        this.item = item;
    }
}
