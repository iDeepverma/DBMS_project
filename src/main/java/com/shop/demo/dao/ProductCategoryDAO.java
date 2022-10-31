package com.shop.demo.dao;

import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDAO {
    List<Product> getAllProductByCategory(String category);
//    select * from Product where Product.name = (select ProductCategory.name from ProductCategory where ProductCategory.category = category);

    int insertProductCategory(ProductCategory productCategory);
//    INSERT INTO ProductCategory VALUES (productCategory);

    int deleteProductCategory(String name);
//    DELETE FROM ProductCategory WHERE ProductCategory.name=name;

    String getProductCategory(String name);

    ProductCategory getProductCategoryByName(String name);

    List<ProductCategory> getAllProductCategory();
//    select category from ProductCategory where ProductCategory.name =name;

}
