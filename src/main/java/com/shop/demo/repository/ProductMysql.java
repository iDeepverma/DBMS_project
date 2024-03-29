package com.shop.demo.repository;

import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository("product_mysql_repo")
public class ProductMysql implements ProductDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertProduct(Product product) {
        String query="INSERT INTO Product(productID,description,warrantyLength,warrantyCoverage,MRP,costPrice,variant,amountInStock,name,photoPath) VALUES (?,?,?,?,?,?,?,?,?,?);";
        Object[] args = new Object[]{
                product.getProductID(),
                product.getDescription(),
                product.getWarrantyLength(),
                product.getWarrantyCoverage(),
                product.getMRP(),
                product.getCostPrice(),
                product.getVariant(),
                product.getAmountInStock(),
                product.getName(),
                product.getPhotoPath()
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
        String query = "UPDATE Product SET description=?, warrantyLength=?,warrantyCoverage=?, MRP=?,costPrice=?,variant=?,amountInStock=?,photoPath=? WHERE productID=?;";
        Object[] args = new Object[]{
                product.getDescription(),
                product.getWarrantyLength(),
                product.getWarrantyCoverage(),
                product.getMRP(),
                product.getCostPrice(),
                product.getVariant(),
                product.getAmountInStock(),
                product.getPhotoPath(),
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
    public int getQuantityByProductByDate(int productID, Date startDate) {
        String query = "SELECT sum(quantity) FROM CustomerOrderItem, CustomerOrder WHERE CustomerOrderItem.productID=? AND CustomerOrderItem.orderID = CustomerOrder.orderID AND CustomerOrder.orderDate >= ?;";
        Object[] args = new Object[]{
                productID,
                startDate
        };
        return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Integer.class));
    }

    @Override
    public List<Product> getAllProduct(){
        String query = "select * from Product ;";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Product.class));
    }


    @Override
    public int markItemSold(int product) {
        String query="UPDATE Product SET amountInStock = amountInStock-1 WHERE Product.productID = ?;";
        Object[] args=new Object[]{
                product
        };
        return jdbcTemplate.update(query,args);
    }
    @Override
    public int getStock(int product) {
        String query="SELECT amountInStock FROM Product WHERE Product.productID=?; ";
        Object[] args =new Object[]{
                product
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Integer.class));
    }
    @Override
    public List<Product> bestProducts(){
        String query="SELECT C.productID,C.name,C.description,C.warrantyLength,C.warrantyCoverage,C.MRP,C.costPrice,C.variant,C.amountInStock,C.photoPath FROM (SELECT A.productID,SUM(quantity) as sales FROM Product A,CustomerOrderItem WHERE CustomerOrderItem.productID = A.productID GROUP BY A.productID) as B, Product as C WHERE C.productID = B.productID ORDER BY B.sales DESC LIMIT 3;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Product.class));
    }
}
