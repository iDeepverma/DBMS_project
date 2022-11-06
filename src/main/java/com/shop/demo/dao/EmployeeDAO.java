package com.shop.demo.dao;

import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    int insertEmployee(Employee employee);
//    INSERT INTO Employee VALUES (employee);

    int deleteEmployee(int id);
//    DELETE FROM Employee WHERE empID = id;

    int updateEmployee(int id, Employee employee);
//    UPDATE Employee SET name=?, dateOfBirth=?,email=?,phone=?,salary=?,joinDate=?,role=?,address=?  WHERE empID=id;

    Employee getEmployeeByID(int id);
//    select * from Employee where empId =id;

    List<CustomerOrder> getServedOrdersByEmployee(int id);
//    select * from CustomerOrder where servedBy=id;



    List<Employee> getAllEmployee();
//    select * from Employee ;

    Employee getOwner();
//    select * from Employee where role="Owner";

    int updatePassword(int id, String password);

    int totalSales(int id);
}
