package com.shop.demo.dao;

import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDAO {
    List<Product> getAllProductByCategory(String category);
//    select * from Product where name = (select name from ProductCategory where category = category);

    int insertProductCategory(ProductCategory productCategory);
//    INSERT INTO ProductCategory VALUES (productCategory);

    int deleteProductCategory(String name);
//    DELETE FROM ProductCategory WHERE name =name;

    String getProductCategory(String name);
//    select category from ProductCategory where name =name;

}
