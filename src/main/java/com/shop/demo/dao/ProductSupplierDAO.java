package com.shop.demo.dao;

import com.shop.demo.model.ProductSuppliers;

public interface ProductSupplierDAO {
    int insertProductSuppliers(ProductSuppliers productSuppliers);
    int deleteProductSuppliers(int id);
    ProductSuppliers getProductSupplierByID(int id);
    int updateProductSuppliers(int id, ProductSuppliers productSuppliers);
}
