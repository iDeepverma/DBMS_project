package com.shop.demo.dao;

import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDAO {
    List<Product> getAllProductByCategory(String category);
    int insertProductCategory(ProductCategory productCategory);
    int deleteProductCategory(String name);
    String getProductCategory(String name);
}
