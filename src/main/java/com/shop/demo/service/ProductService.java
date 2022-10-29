package com.shop.demo.service;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.time.LocalDateTime;

public class ProductService {
    private ProductDAO productDAO;
    @Autowired
    public ProductService(@Qualifier("product_mysql_repo") ProductDAO productDAO) {this.productDAO = productDAO;
    }
    public int insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    public Product getProductByID(int id) {
        return productDAO.getProductByID(id);
    }

    public int updateProduct(int id, Product product){
        return productDAO.updateProduct(id,product);
    }

    public int deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    public int getQuantityByProductByDate(Product product, LocalDateTime startDate) {
        return productDAO.getQuantityByProductByDate(product, startDate);
    }
}
