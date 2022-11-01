package com.shop.demo.appController;

import com.shop.demo.dao.*;
import com.shop.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

    @Autowired
    private CustomerOrderItemDAO customerOrderItemDAO;

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
    public String insertEmployee(@ModelAttribute Employee employee) {
        System.out.println("Email : " + employee.getEmail());
        System.out.println("Recieved post");
        int x = employeeDAO.insertEmployee(employee);
        return "redirect:/getAllEmployee/";
    }
//    @GetMapping("/getOwner")
//    public String getOwner(Model model){
//        Employee owner = new Employee();
////              owner = employeeDAO.getOwner();
//              model.addAttribute("owner",owner);
//              return "test";
//    }

    @GetMapping("/employeeDetails")
    public String employeeDetails(Model model,@RequestParam("id") String id){
        Employee employee = employeeDAO.getEmployeeByID(Integer.parseInt(id));
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

    @GetMapping("/performance")
    public String performance(Model model){
        List<ProductCategory>category = productCategoryDAO.getAllProductCategory();
        List<Integer>profit = new ArrayList<>();
        List<Integer>revenue = new ArrayList<>();
        List<String>Category = new ArrayList<>();
        int temp = 0;
        for(int i=0;i<category.size();i++){
            int totalrevenue = 0,totalprofit = 0;
            String eachcat = category.get(i).getCategory();
            List<Product>temp1 = productCategoryDAO.getAllProductByCategory(eachcat);
            List<CustomerOrderItem>allitems = customerOrderItemDAO.getAllCustomerOrderItem();
            for(int j=0;j<temp1.size();j++){
                int id = temp1.get(j).getProductID();
                for(int k=0;k<allitems.size();k++){
                    if(allitems.get(k).getProduct().getProductID() ==  id){
                        totalrevenue = totalrevenue + (allitems.get(k).getQuantity())*(allitems.get(k).getSellingPrice());
                        totalprofit = totalprofit + (customerOrderItemDAO.getProfitPerOrderItem(allitems.get(k)));
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
