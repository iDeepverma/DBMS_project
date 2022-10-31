package com.shop.demo.api;

import com.shop.demo.model.Employee;
import com.shop.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@RestController
//@RequestMapping("api/v1/employee")
public class EmployeeApi {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeApi(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployee(){

        return employeeService.getAllEmployee();
    }

    @DeleteMapping(path = "{id}")
    public int deleteEmployee(@PathVariable("id") int id){
        return employeeService.deleteEmployee(id);
    }

    @PostMapping
    public int insertEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @PutMapping(path = "{id}")
    public int updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @GetMapping(path = "{id}")
    public Employee getEmployeeByID(@PathVariable("id") int id){
        return employeeService.getEmployeeByID(id);
    }

}

