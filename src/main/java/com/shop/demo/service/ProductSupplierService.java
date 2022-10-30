package com.shop.demo.service;

import com.shop.demo.dao.ProductDAO;
import com.shop.demo.dao.ProductSupplierDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductSuppliers;
import com.shop.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductSupplierService {
    private ProductSupplierDAO productSupplierDAO;
    @Autowired
    public ProductSupplierService(@Qualifier("productSupplierService_mysql_repo") ProductSupplierDAO productSupplierDAO) {this.productSupplierDAO = productSupplierDAO;
    }
    public int insertProductSuppliers(ProductSuppliers productSuppliers) {
        return productSupplierDAO.insertProductSuppliers(productSuppliers);
    }

    public int deleteProductSuppliers(Product product, Supplier supplier) {
        return productSupplierDAO.deleteProductSuppliers(product , supplier);
    }

    public ProductSuppliers getProductsOfSuppliers(int supplierID) {
        return productSupplierDAO.getProductsOfSuppliers(supplierID);
    }

    public ProductSuppliers getSuppliersOfProduct(int productID) {
        return productSupplierDAO.getSuppliersOfProduct(productID);
    }
}
