package com.shop.demo.repository;

import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("product_mysql_repo")
public class ProductMysql implements ProductDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertProduct(Product product) {
        String query="INSERT INTO Product(productID,description,warrantyLength,warrantyCoverage,MRP,costPrice,variant,amountInStock,name) VALUES (?,?,?,?,?,?,?,?,?);";
        Object[] args = new Object[]{
                product.getProductID(),
                product.getDescription(),
                product.getWarrantyLength(),
                product.getWarrantyCoverage(),
                product.getMRP(),
                product.getCostPrice(),
                product.getVariant(),
                product.getAmountInStock(),
                product.getProductCategory()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public Product getProductByID(int id) {
        String query="select * from Product where productID=?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public int updateProduct(int id, Product product) {
        String query = "UPDATE Product SET description=?, warrantyLength=?,warrantyCoverage=?, MRP=?,costPrice=?,variant=?,amountInStock=? WHERE productID=?;";
        Object[] args = new Object[]{
                product.getDescription(),
                product.getWarrantyLength(),
                product.getWarrantyCoverage(),
                product.getMRP(),
                product.getCostPrice(),
                product.getVariant(),
                product.getAmountInStock(),
                product.getProductCategory(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE productID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int getQuantityByProductByDate(Product product, LocalDateTime startDate) {
        String query = "SELECT sum(quantity) FROM CustomerOrderItem, CustomerOrder WHERE CustomerOrderItem.productID=? AND CustomerOrderItem.orderID = CustomerOrder.orderID AND CustomerOrder.orderDate >= ?;";
        Object[] args = new Object[]{
                product.getProductID(),
                startDate
        };
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Integer.class));
    }
}
