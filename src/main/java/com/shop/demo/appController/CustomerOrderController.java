package com.shop.demo.appController;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerOrderController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/customerOrder")
    public String getAllcustomerOrders(Model model){
        List<CustomerOrder> temp = customerOrderDAO.getAllCustomerOrders();
        List<CustomerOrder>orders = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            orders.add(temp.get(i));
        }
        model.addAttribute("orders",orders);
        return "dashboard/customerOrders/customerOrders.html";
    }

    @GetMapping ("/customerOrder/create")
    public String createCustomerOrder(Model model)
    {
        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Customer>customer = customerDAO.getAllCustomer();
        model.addAttribute("employee" , employee);
        model.addAttribute("customer" , customer);
        CustomerOrder customerOrder = new CustomerOrder();
        model.addAttribute("customerOrder", customerOrder);
        return "dashboard/customerOrders/customerOrdersCreate";
    }

    @PostMapping("/customerOrder/create")
    public String createCustomerOrderPost(@ModelAttribute("customerOrder") CustomerOrder customerOrder)
    {
        customerOrderDAO.insertCustomerOrder(customerOrder);
        return "redirect:/customerOrder/";
    }

    @GetMapping ("/customerOrders/edit/{id}")
    public String editCustomerOrder(@PathVariable("id") int id, Model model)
    {
        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Customer>customer = customerDAO.getAllCustomer();
        model.addAttribute("employee" , employee);
        model.addAttribute("customer" , customer);
        CustomerOrder customerOrder = customerOrderDAO.getCustomerOrderByID(id);
        model.addAttribute("customerOrder", customerOrder);
        return "dashboard/customerOrders/customerOrdersEdit";
    }

    @PostMapping ("/customerOrders/edit/{id}")
    public String editCustomerOrderPost(@PathVariable("id") int id, @ModelAttribute("customerOrder") CustomerOrder customerOrder)
    {
        customerOrderDAO.updateCustomerOrder(id, customerOrder);
        return "redirect:/customerOrder/";
    }
}
