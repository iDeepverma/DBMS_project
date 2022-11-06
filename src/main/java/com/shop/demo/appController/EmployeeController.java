package com.shop.demo.appController;

import com.shop.demo.dao.*;
import com.shop.demo.model.*;
import com.shop.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


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
    private SupplierDAO supplierDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerOrderItemDAO customerOrderItemDAO;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping ("/getAllEmployee")
    public String getAllEmployee(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Employee> employees =employeeDAO.getAllEmployee();
        model.addAttribute( "employees",employees);
        return "allEmployee";
    }

    @GetMapping("/getAllEmployee/new")
    public String CreateEmployee(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
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
    public String employeeDetails(Model model,@RequestParam("id") String id, HttpSession session){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
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
    @GetMapping("/allCustomer")
    public String allCustomer(Model model, HttpSession session){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Customer> customers =customerDAO.getAllCustomer();
        model.addAttribute( "customer",customers);
        return "allCustomer";
    }




    @GetMapping ("/employee")
    public String listEmployee(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Employee> employee = employeeDAO.getAllEmployee();
        List<Integer> sales = new ArrayList<>();
        for(int i=0;i<employee.size();i++){
            Employee emp = employee.get(i);
            List<CustomerOrder>orders = employeeDAO.getServedOrdersByEmployee(emp.getEmpID());
            int total = 0;
            for(int j=0;j<orders.size();j++){
                total = total + orders.get(j).getTotal();
            }
            sales.add(total);
        }
        model.addAttribute("employee", employee);
        model.addAttribute("sales",sales);
        return "dashboard/employee/employeeList";
    }
    @GetMapping ("/employee/create")
    public String createEmployee(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "dashboard/employee/employeeCreate.html";
    }

    @PostMapping("/employee/create")
    public String createEmployeePost(@ModelAttribute("employee") Employee employee, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        employeeDAO.insertEmployee(employee);
        return "redirect:/employee/";
    }
    @GetMapping ("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        employeeDAO.deleteEmployee(id);
        return "redirect:/employee/";
    }
    @GetMapping ("/employee/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        Employee employee = employeeDAO.getEmployeeByID(id);
        model.addAttribute("employee", employee);
        return "dashboard/employee/employeeEdit.html";
    }

    @PostMapping ("/employee/edit/{id}")
    public String editCustomerPost(@PathVariable("id") int id, @ModelAttribute("employee") Employee employee, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        employeeDAO.updateEmployee(id,employee);
        return "redirect:/employee/";
    }
    @GetMapping("/customers/view/{id}")
    public String searchCustomer(@PathVariable("id") int id,Model model, HttpSession session) {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<CustomerOrder> orders = customerOrderDAO.getCustomerOrderByCustomer(id);
        model.addAttribute("orders" , orders);
        return "dashboard/customers/customerSearch";
    }


}
