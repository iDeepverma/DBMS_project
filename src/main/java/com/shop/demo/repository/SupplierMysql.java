package com.shop.demo.repository;

import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//        create table Supplier(
//        name varchar(255),
//        supplierID int AUTO_INCREMENT,
//        phone int,
//        address varchar(255),
//        moneySpent int,
//        ordersFulfilled int,
//        email varchar(255),
//        PRIMARY KEY (supplierID)
//        );

@Repository("supplier_mysql_repo")
public class SupplierMysql implements SupplierDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertSupplier(Supplier supplier) {
        String query = "INSERT INTO Supplier(name,phone,address,moneySpent,ordersFulfilled,email) VALUES (?,?,?,?,?,?);";
        Object[] args = new Object[] {
                supplier.getName(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getMoneySpent(),
                supplier.getOrdersFulfilled(),
                supplier.getEmail()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteSupplier(int id) {
        String query = "DELETE FROM Supplier WHERE supplierID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateSupplier(int id, Supplier supplier) {
        String query = "UPDATE Supplier SET name=?, phone=?,address=?, moneySpent=?,ordersFulfilled=?,email=? WHERE supplierID=?;";
        Object[] args = new Object[]{
                supplier.getName(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getMoneySpent(),
                supplier.getOrdersFulfilled(),
                supplier.getEmail(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public Supplier getSupplierByID(int id) {
        String query = "select * from Supplier where supplierID=?;";
        Object[] args = new Object[]{
                id
        };
        try {
            return jdbcTemplate.queryForObject(query, args, BeanPropertyRowMapper.newInstance(Supplier.class));
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Supplier Id doesnt exist");
            return null;
        }
    }

    @Override
    public int updateMoney(int id, int money) {
        String query = "UPDATE Supplier SET moneySpent=? WHERE supplierID=?;";
        Object[] args = new Object[]{
                money,
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateOrdersFullfilled(int id, int orders) {
        String query = "UPDATE Supplier SET ordersFulfilled=? WHERE supplierID=?;";
        Object[] args = new Object[]{
                orders,
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Supplier> getAllSupplier() {
        String query = "SELECT * FROM Supplier;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Supplier.class));
    }


}
