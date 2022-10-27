package com.shop.demo.dao;

import com.shop.demo.model.Product;
import com.shop.demo.model.ProductSuppliers;
import com.shop.demo.model.Supplier;

public interface ProductSupplierDAO {
    int insertProductSuppliers(ProductSuppliers productSuppliers);
    int deleteProductSuppliers(Product product, Supplier supplier);
    ProductSuppliers getProductSupplierByID(int id);
}
