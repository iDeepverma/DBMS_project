package com.shop.demo.appController;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class EmployeeController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping ("/getAllEmployee")
    public String getAllEmployee(Model model)
    {
        List<Employee> employees =employeeDAO.getAllEmployee();
        model.addAttribute( "employees",employees);
        return "test";


    }

    @GetMapping("/getAllEmployee/new")
    public String CreateEmployee(Model model)
    {
        System.out.println("form called");
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "save";
    }

    @PostMapping("/i/")
    public String InsertEmployee(@ModelAttribute Employee employee) {
        System.out.println("Email : " + employee.getEmail());
        System.out.println("Recieved post");
        int x = employeeDAO.insertEmployee(employee);
        return "redirect:/getAllEmployee/";
    }

}
