package com.shop.demo.dao;

import com.shop.demo.model.Employee;

public interface EmployeeDAO {
    int insertEmployee(Employee employee);
    int deleteEmployee(int id);
    int updateEmployee(int id, Employee employee);
    Employee getEmployeeByID(int id);
    Employee getOwner();
}
