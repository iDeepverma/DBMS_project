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
    private SupplierDAO supplierDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerOrderItemDAO customerOrderItemDAO;

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
    @GetMapping("/allCustomer")
    public String allCustomer(Model model){
        List<Customer> customers =customerDAO.getAllCustomer();
        model.addAttribute( "customer",customers);
        return "allCustomer";
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

    @PostMapping ("/customers/edit/{id}")
    public String editCustomerPost(@PathVariable("id") int id, @ModelAttribute("customer") Customer customer)
    {
        customerDAO.updateCustomer(id, customer);
        return "redirect:/customers/";
    }

    @GetMapping("/performance")
    public String performance(Model model){
        List<ProductCategory>category = productCategoryDAO.getAllProductCategory();
        List<Long>profit = new ArrayList<>();
        List<Long>revenue = new ArrayList<>();
        List<String>Category = new ArrayList<>();
        int temp = 0;
        for(int i=0;i<category.size();i++){
            long totalrevenue = 0,totalprofit = 0;
            String eachcat = category.get(i).getCategory();
            List<Product>temp1 = productCategoryDAO.getAllProductByCategory(eachcat);
            List<CustomerOrderItem>allitems = customerOrderItemDAO.getAllCustomerOrderItem();
            for(int j=0;j<temp1.size();j++){
                long id = temp1.get(j).getProductID();
                for(int k=0;k<allitems.size();k++){
                    if(allitems.get(k).getProductID() ==  id){
                        totalrevenue = totalrevenue + (allitems.get(k).getQuantity())*(allitems.get(k).getSellingPrice());
                        System.out.println(totalrevenue);
                        totalprofit = totalprofit + (allitems.get(k).getQuantity()*(allitems.get(k).getSellingPrice() - productDAO.getProductByID(allitems.get(i).getProductID()).getCostPrice()));
                    }
                }
            }
            profit.add(totalprofit);
            revenue.add(totalrevenue);
            Category.add(eachcat);
        }
        model.addAttribute("profit",profit);
        model.addAttribute("revenue",revenue);
        model.addAttribute("Category",Category);
        return "performance";
    }
}
