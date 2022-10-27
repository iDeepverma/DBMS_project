package com.shop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSuppliers {
    private Product product;
    private Supplier supplier;

    public Product getProductID() {
        return product;
    }

    public void setProductID(Product productID) {
        this.product = productID;
    }

    public Supplier getSupplierID() {
        return supplier;
    }

    public void setSupplierID(Supplier supplierID) {
        this.supplier = supplierID;
    }


}