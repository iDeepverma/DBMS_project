package com.shop.demo.dao;

import com.shop.demo.model.Product;

import java.util.List;

public interface ProductDAO {
    int insertProduct(Product product);
    int getProductByID(int id);
    int updateProduct(int id, Product product);
    int deleteProduct(int id);
}
