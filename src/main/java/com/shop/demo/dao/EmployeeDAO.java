package com.shop.demo.dao;

import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    int insertEmployee(Employee employee);
    int deleteEmployee(int id);
    int updateEmployee(int id, Employee employee);
    Employee getEmployeeByID(int id);
    List<CustomerOrder> getServedOrdersByEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getOwner();
}
