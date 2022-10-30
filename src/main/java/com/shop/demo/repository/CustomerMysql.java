package com.shop.demo.repository;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository("customer_mysql_repo")
public class CustomerMysql implements CustomerDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertCustomer(Customer customer) {
        String query = "INSERT INTO Customer(name,phone,email,DOB,address) VALUES (?,?,?,?,?);";
        Object[] args = new Object[] {
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getDOB().toString(),
                customer.getAddress()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public Customer getCustomerByID(int id) {
        String query = "SELECT * FROM Customer WHERE customerID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public Customer getCustomerByNumber(String phone) {
        String query = "SELECT * FROM Customer WHERE Customer.phone=?;";
        Object[] args = new Object[]{
                phone
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        String query = "SELECT * FROM Customer WHERE Customer.email = ?;";
        Object[] args = new Object[]{
                email
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int updateCustomer(int id, Customer customer) {
        String query="UPDATE Customer SET name=?,phone=?,email=?,DOB=?,address=? WHERE customerID = ?;";
        Object[] args = new Object[]{
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getDOB().toString(),
                customer.getAddress(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteCustomer(int id) {
        String query="DELETE FROM Customer WHERE customerID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Customer> getAllCustomer() {
        String query = "select * from Customer;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Customer.class));
    }
}
