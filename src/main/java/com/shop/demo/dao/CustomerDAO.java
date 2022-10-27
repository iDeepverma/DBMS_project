package com.shop.demo.dao;

import com.shop.demo.model.Customer;

public interface CustomerDAO {
    int insertCustomer(Customer customer);
    Customer getCustomerByID(int id);
    Customer getCustomerByNumber(String phone);
    Customer getCustomerByEmail(String email);
    int updateCustomer(int id,Customer customer);
    int deleteCustomer(int id);
}

