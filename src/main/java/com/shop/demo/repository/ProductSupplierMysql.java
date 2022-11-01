package com.shop.demo.repository;

import com.shop.demo.dao.ProductSupplierDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductSuppliers;
import com.shop.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productSupplier_mysql_repo")
public class ProductSupplierMysql implements ProductSupplierDAO {

//    supplierID int,
//    productID int,
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertProductSuppliers(ProductSuppliers productSuppliers) {
        String query ="INSERT INTO ProductSuppliers(supplierID , productID) VALUES (?,?);";
        Object[] args=new Object[]{
                productSuppliers.getSupplierID(),
                productSuppliers.getProductID()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteProductSuppliers(Product product, Supplier supplier) {
        String query ="DELETE FROM ProductSuppliers WHERE ProductSuppliers.productID=? AND ProductSuppliers.supplierID=?;";
        Object[] args =new Object[]{
                product.getProductID(),
                supplier.getSupplierID()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public ProductSuppliers getProductsOfSuppliers(int supplierID) {
        String query="SELECT * FROM ProductSuppliers WHERE ProductSuppliers.supplierID = ?;";
        Object[] args =new Object[]{
                supplierID
        };

        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(ProductSuppliers.class));
    }

    @Override
    public ProductSuppliers getSuppliersOfProduct(int productID) {
        String query ="SELECT * FROM ProductSuppliers WHERE ProductSuppliers.productID=?;";
        Object[] args =new Object[]{
                productID
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(ProductSuppliers.class));
    }

    @Override
    public List<ProductSuppliers> getAllProductSuppliers(){
        String query = "select * from ProductSuppliers ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(ProductSuppliers.class));

    }
}
