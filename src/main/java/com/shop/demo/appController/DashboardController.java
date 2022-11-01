package com.shop.demo.appController;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/dashboard")
    public String showDashboard(Model model)
    {
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

    @GetMapping ("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model)
    {
        Customer customer = customerDAO.getCustomerByID(id);
        model.addAttribute("customer", customer);
        return "dashboard/customers/customerEdit.html";
    }
}
