package com.shop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSuppliers {
    private Product productID;
    private Supplier supplierID;

    public Product getProduct() {
        return productID;
    }

    public void setProduct(Product productID) {
        this.productID = productID;
    }

    public Supplier getSupplier() {
        return supplierID;
    }

    public void setSupplier(Supplier supplierID) {
        this.supplierID = supplierID;
    }
}