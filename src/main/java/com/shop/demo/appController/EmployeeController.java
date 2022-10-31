package com.shop.demo.appController;

import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private ProductDAO productDAO;
    @GetMapping ("/getAllEmployee")
    public String getAllEmployee(Model model)
    {
        List<Employee> employees =employeeDAO.getAllEmployee();
        model.addAttribute( "employees",employees);
        return "allEmployee";

    }

    @GetMapping("/getAllEmployee/new")
    public String CreateEmployee(Model model)
    {
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "save";
    }

    @PostMapping("/i/")
    public String insertEmployee(@ModelAttribute Employee employee) {

        int x = employeeDAO.insertEmployee(employee);
        return "redirect:/getAllEmployee/";
    }


    @GetMapping("/employeeDetails")
    public String employeeDetails(Model model,@RequestParam("id") String id){
        Employee employee;
        try{
            employee = employeeDAO.getEmployeeByID(Integer.parseInt(id));
        }
        catch (EmptyResultDataAccessException e){
            return "errror";
        }
        List<CustomerOrder> customerOrders= employeeDAO.getServedOrdersByEmployee(Integer.parseInt(id));

        int x = 0;
        for(int i=0;i<customerOrders.size();i++){
            x = x + customerOrders.get(i).getTotal();
        }
        model.addAttribute("employee",employee);
        model.addAttribute("total",x);
        return "test";
    }

}
