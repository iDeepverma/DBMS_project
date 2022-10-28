package com.shop.demo.repository;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository("mysql_repo")
public class EmployeeMysql implements EmployeeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertEmployee(Employee employee) {
        // returns 0 if unsucessfull else returns none;
        String query = "INSERT INTO Employee(name,DOB,email,phone,salary,joinDate,role,address) VALUES(?,?,?,?,?,?,?,?);";
        Object[] args = new Object[] {
                employee.getName(),
                employee.getDOB().toString(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getSalary(),
                employee.getJoinDate().toString(),
                employee.getRole(),
                employee.getAddress()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE empID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateEmployee(int id, Employee employee) {
        String query = "UPDATE Employee SET name=?, DOB=?,email=?,phone=?,salary=?,joinDate=?,role=?,address=?  WHERE empID=?;";
        Object[] args = new Object[]{
                employee.getName(),
                employee.getDOB().toString(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getSalary(),
                employee.getJoinDate().toString(),
                employee.getRole(),
                employee.getAddress(),
                id
        };
        return jdbcTemplate.update(query,args);
    };

    @Override
    public Employee getEmployeeByID(int id) {
        String query = "select * from Employee where empId =?";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args,BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public List<CustomerOrder> getServedOrdersByEmployee(int id) {
        String query = "select * from CustomerOrder where servedBy=?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.query(query,args,BeanPropertyRowMapper.newInstance(CustomerOrder.class));
    }

    @Override
    public List<Employee> getAllEmployee() {
        String query = "select * from Employee ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public Employee getOwner() {
        String query = "select * from Employee where role=\"Owner\";";
        return jdbcTemplate.queryForObject(query,BeanPropertyRowMapper.newInstance(Employee.class));
    }
}
