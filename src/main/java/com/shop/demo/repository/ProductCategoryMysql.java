package com.shop.demo.repository;

import com.shop.demo.dao.ProductCategoryDAO;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mysql_repo")

public class ProductCategoryMysql implements ProductCategoryDAO {

//    name varchar(255),
//    category varchar(255),

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProductByCategory(String category) {
        String query ="select * from Product where Product.name = (select ProductCategory.name from ProductCategory where ProductCategory.category = ?);";
        Object[] args=new Object[]{
          category
        };
        return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public int insertProductCategory(ProductCategory productCategory) {
        String query="INSERT INTO ProductCategory(name,category) VALUES (?,?);";
        Object[] args =new Object[]{
          productCategory.getName(),
          productCategory.getCategory()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteProductCategory(String name) {
        String query="DELETE FROM ProductCategory WHERE ProductCategory.name=?;";
        Object[] args=new Object[]{
           name
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public String getProductCategory(String name) {
        String query ="select category from ProductCategory where ProductCategory.name =?;";
        Object[] args=new Object[]{
                name
        };
        return jdbcTemplate.queryForObject(query,args,BeanPropertyRowMapper.newInstance(String.class));
    }

}
