package com.shop.demo.service;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.ProductCategoryDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private ProductCategoryDAO productCategoryDAO;
    @Autowired
    public ProductCategoryService(@Qualifier("productCategory_mysql_repo") ProductCategoryDAO productCategoryDAO) {this.productCategoryDAO = productCategoryDAO;
    }
    public List<Product> getAllProductByCategory(String category) {
        return productCategoryDAO.getAllProductByCategory(category);
    }
    public int insertProductCategory(ProductCategory productCategory) {
        return productCategoryDAO.insertProductCategory(productCategory);
    }
    public int deleteProductCategory(String name) {
        return productCategoryDAO.deleteProductCategory(name);
    }
    public String getProductCategory(String name) {
        return productCategoryDAO.getProductCategory(name);
    }

    public List<ProductCategory> getAllProductCategory(){ return productCategoryDAO.getAllProductCategory();}

    public ProductCategory getProductCategoryByName(String name){
        return productCategoryDAO.getProductCategoryByName(name);
    }
}
