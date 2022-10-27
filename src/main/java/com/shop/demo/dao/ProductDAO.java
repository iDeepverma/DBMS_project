package com.shop.demo.dao;

import com.shop.demo.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductDAO {
    int insertProduct(Product product);
    Product getProductByID(int id);
    int updateProduct(int id, Product product);
    int deleteProduct(int id);

    int getQuantityByProductByDate(Product product, LocalDateTime startDate);
}
