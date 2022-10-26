package com.shop.demo.dao;

import com.shop.demo.model.Customer;

public interface CustomerDAO {
    int insertCustomer(Customer customer);
    Customer getCustomerByID(int id);
    int updateCustomer(int id,Customer customer);
    int deleteCustomer(int id);
}
