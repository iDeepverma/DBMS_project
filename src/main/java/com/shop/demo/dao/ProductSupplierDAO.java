package com.shop.demo.dao;

import com.shop.demo.model.Product;
import com.shop.demo.model.ProductSuppliers;
import com.shop.demo.model.Supplier;

import java.util.List;

public interface ProductSupplierDAO {
    int insertProductSuppliers(ProductSuppliers productSuppliers);
    //INSERT INTO ProductSuppliers VALUES (productSuppliers);
    int deleteProductSuppliers(Product product, Supplier supplier);
    //DELETE FROM ProductSuppliers WHERE product.productID = ProductSuppliers.productID AND supplier.supplierID=ProductSuppliers.supplierID;
    ProductSuppliers getProductsOfSuppliers(int supplierID);
    //SELECT * FROM ProductSuppliers WHERE ProductSuppliers.supplierID = id;
    ProductSuppliers getSuppliersOfProduct(int productID);

    List<ProductSuppliers> getAllProductSuppliers();
    //SELECT * FROM ProductSuppliers WHERE ProductSuppliers.productID = productID;
}
