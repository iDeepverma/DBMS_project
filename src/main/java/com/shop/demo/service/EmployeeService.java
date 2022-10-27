package com.shop.demo.service;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(@Qualifier("mysql_repo") EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public int insertEmployee(Employee employee){
        return employeeDAO.insertEmployee(employee);
    }

    public int deleteEmployee(int id){
        return employeeDAO.deleteEmployee(id);
    }

    public int updateEmployee(int id, Employee employee){
        return employeeDAO.updateEmployee(id,employee);
    }

    public Employee getEmployeeByID(int id){
        return employeeDAO.getEmployeeByID(id);
    }

    public List<CustomerOrder> getServedOrdersByEmployee(int id){
        return employeeDAO.getServedOrdersByEmployee(id);
    }

    public List<Employee> getAllEmployee(){
        return employeeDAO.getAllEmployee();
    }

    public Employee getOwner(){
        return employeeDAO.getOwner();
    }
}
