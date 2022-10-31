package com.shop.demo.appController;

import com.shop.demo.dao.*;
import com.shop.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


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
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private CustomerDAO customerDAO;
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
    @GetMapping("/productByCategory")
    public String productByCategory(Model model){
        List<List<Product>>productCategorri = new ArrayList<>();
        List<ProductCategory>category;
        category = productCategoryDAO.getAllProductCategory();
        for(int i=0;i<category.size();i++){
            List<Product>temp =new Vector<Product>();
            temp = productCategoryDAO.getAllProductByCategory(category.get(i).getCategory());
            for(int j=0;j<temp.size();j++){
                System.out.println(temp.get(j).getProductID()+ " ");
            }
            System.out.println(category.get(i).getCategory());
            productCategorri.add(temp);
        }
        model.addAttribute("productCategorri",productCategorri);
        model.addAttribute("category", category);
        return "test";
    }
    @GetMapping("/allCustomer")
    public String allCustomer(Model model){
        List<Customer> customers =customerDAO.getAllCustomer();
        model.addAttribute( "customer",customers);
        return "allCustomer";
    }


    @GetMapping("/stockAvailability")
    public String stockAvailability(Model model){
        List<Product>temp = productDAO.getAllProduct();
        List<String>category = new ArrayList<>();
        List<Integer>stocks = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            System.out.println(temp.get(i).getProductCategory().getName());
            category.add(temp.get(i).getProductCategory().getName());
            stocks.add(temp.get(i).getAmountInStock());
        }
        model.addAttribute("names",category);
        model.addAttribute("stocks",stocks);
        return "stockAvailability";
    }


    @GetMapping ("/dashboard")
    public String showDashboard()
    {
        return "dashboard/dashboard";
    }
    @GetMapping ("/customers")
    public String listCustomers(Model model)
    {
        List<Customer> customers = customerDAO.getAllCustomer();
        model.addAttribute("customers", customers);
        return "dashboard/customers/customerList";
    }

    @GetMapping ("/customers/create")
    public String createCustomer(Model model)
    {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "dashboard/customers/customerCreate.html";
    }
    @PostMapping ("/customers/create")
    public String createCustomerPost(@ModelAttribute("customer") Customer customer)
    {
        customerDAO.insertCustomer(customer);
        return "redirect:/customers/";
    }
    @GetMapping ("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id)
    {
        customerDAO.deleteCustomer(id);
        return "redirect:/customers/";
    }
    @GetMapping ("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model)
    {
        Customer customer = customerDAO.getCustomerByID(id);
        model.addAttribute("customer", customer);
        return "dashboard/customers/customerEdit.html";
    }
    @PostMapping ("/customers/edit/{id}")
    public String editCustomerPost(@PathVariable("id") int id, @ModelAttribute("customer") Customer customer)
    {
        customerDAO.updateCustomer(id, customer);
        return "redirect:/customers/";
    }

}
