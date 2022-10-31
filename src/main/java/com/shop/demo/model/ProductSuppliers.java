package com.shop.demo.model;

import com.shop.demo.repository.ProductMysql;
import com.shop.demo.repository.SupplierMysql;
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

    public void setProduct(int productID) {
        ProductMysql productMysql = new ProductMysql();
        this.productID = productMysql.getProductByID(productID);
    }

    public Supplier getSupplier() {
        return supplierID;
    }

    public void setSupplier(int supplierID) {
        SupplierMysql supplierMysql = new SupplierMysql();
        this.supplierID = supplierMysql.getSupplierByID(supplierID);
    }
}