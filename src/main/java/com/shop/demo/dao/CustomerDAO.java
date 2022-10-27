package com.shop.demo.dao;

import com.shop.demo.model.Customer;

public interface CustomerDAO {
    int insertCustomer(Customer customer);
    //INSERT INTO Customer VALUES (customer);
    Customer getCustomerByID(int id);
    //SELECT * FROM Customer WHERE customerID = id;
    Customer getCustomerByNumber(String phone);
    //SELECT * FROM Customer WHERE Customer.phone=phone;
    Customer getCustomerByEmail(String email);
    //SELECT * FROM Customer WHERE Customer.email = email;
    int updateCustomer(int id,Customer customer);
    //UPDATE Customer SET name=?,phone=?,email=?,dateOfBirth=?,address=? WHERE customerID = id;
    int deleteCustomer(int id);
    //DELETE FROM Customer WHERE customerID = id;
}

