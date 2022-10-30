package com.shop.demo.service;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerDAO customerDAO;
    @Autowired
    public CustomerService(@Qualifier("customer_mysql_repo") CustomerDAO customerDAO){  this.customerDAO = customerDAO;   }

    public int insertCustomer(Customer customer) {
        return customerDAO.insertCustomer(customer);
    }

    public Customer getCustomerByID(int id) {
        return customerDAO.getCustomerByID(id);
    }
    public Customer getCustomerByNumber(String phone) {
        return customerDAO.getCustomerByNumber(phone);
    }


    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }


    public int updateCustomer(int id, Customer customer) {
        return customerDAO.updateCustomer(id,customer);
    }

    public int deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }



}
