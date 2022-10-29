package com.shop.demo.service;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    public CustomerService(@Qualifier("mysql_repo") EmployeeDAO employeeDAO){  this.employeeDAO = employeeDAO;   }

    public int insertCustomer(Customer customer){
        return CustomerDAO.insertCustomer(customer);
    }

    public int deleteCustomer(int id){  return CustomerDAO.deleteCustomer(id);   }

    public int updateCustomer(int id, Customer customer){
        return customerDAO.updateCustomer(id, customer);
    }



}
