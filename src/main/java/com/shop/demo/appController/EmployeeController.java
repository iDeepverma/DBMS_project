package com.shop.demo.appController;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class EmployeeController {
    @Autowired
    private EmployeeDAO dao;

    @GetMapping ("/getAllEmployee")
    public String getAllEmployee(Model model)
    {
        List<Employee> employee =dao.getAllEmployee();
        model.addAttribute( "employee",employee);
        return "test";


    }

    @GetMapping("/getAllEmployee/new")
    public String CreateEmployee(Model model)
    {
        Employee employee=new Employee();
        System.out.println(employee.getEmpID());
        System.out.println(employee.getName());
        System.out.println(employee.getDOB());
        model.addAttribute("employee",employee);
        return "save";
    }

    @PostMapping("/InsertEmployee")
    public int InsertEmployee(@ModelAttribute("employee") Employee employee) {
        System.out.println(employee.getEmpID());
        System.out.println(employee.getName());
        System.out.println(employee.getDOB());
        int x = dao.insertEmployee(employee);
        System.out.println(x);
        return x;
    }

}
