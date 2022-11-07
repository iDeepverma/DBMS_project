package com.shop.demo.appController;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.InventoryItem;
import com.shop.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller

public class DashboardController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<CustomerOrder> temp = customerOrderDAO.getAllCustomerOrders();
        List<CustomerOrder>orders = new ArrayList<>();
        Collections.reverse(temp);
        for(int i=0;i<temp.size();i++){
            orders.add(temp.get(i));
            if(i==10){
                break;
            }
        }

        model.addAttribute("orders",orders);
        return "dashboard/dashboard";
    }

    @GetMapping ("/customers")
    public String listCustomers(Model model, HttpSession session, @RequestParam(required = false) String id)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Customer> customers = new ArrayList<>();
        if (id == null || id == "") {
            customers = customerDAO.getAllCustomer();
        }
        else {
            int intid = Integer.parseInt(id);
            try {
                Customer cust = customerDAO.getCustomerByID(intid);
                customers.add(cust);
            }
            catch (Exception e) {
                System.out.println("Customer NOT FOUND!");
//                System.out.println(e);
            }
        }
        model.addAttribute("customers", customers);
        return "dashboard/customers/customerList";
    }

    @GetMapping ("/customers/create")
    public String createCustomer(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "dashboard/customers/customerCreate.html";
    }


    @PostMapping("/customers/create")
    public String createCustomerPost(@ModelAttribute("customer") Customer customer, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        customerDAO.insertCustomer(customer);
        return "redirect:/customers/";
    }

    @GetMapping ("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        customerDAO.deleteCustomer(id);
        return "redirect:/customers/";
    }

    @GetMapping ("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        Customer customer = customerDAO.getCustomerByID(id);
        model.addAttribute("customer", customer);
        return "dashboard/customers/customerEdit.html";
    }

    @PostMapping ("/customers/edit/{id}")
    public String editCustomerPost(@PathVariable("id") int id, @ModelAttribute("customer") Customer customer, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        customerDAO.updateCustomer(id, customer);
        return "redirect:/customers/";
    }
}
