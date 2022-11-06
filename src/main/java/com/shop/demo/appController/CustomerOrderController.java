package com.shop.demo.appController;

import com.shop.demo.dao.*;
import com.shop.demo.model.*;
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

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CustomerOrderItemDAO customerOrderItemDAO;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private InventoryItemDAO inventoryItemDAO;
    @GetMapping("/customerOrder")
    public String getAllcustomerOrders(Model model, HttpSession session){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<CustomerOrder> temp = customerOrderDAO.getAllCustomerOrders();
        List<CustomerOrder>orders = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            orders.add(temp.get(i));
        }
        model.addAttribute("orders",orders);
        return "dashboard/customerOrders/customerOrders.html";
    }

    @GetMapping ("/customerOrder/create")
    public String createCustomerOrder(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        int id = authenticationService.getCurrentUser(session);
        model.addAttribute("id" , id);
        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Customer>customer = customerDAO.getAllCustomer();
        model.addAttribute("employee" , employee);
        model.addAttribute("customer" , customer);
        CustomerOrder customerOrder = new CustomerOrder();
        model.addAttribute("customerOrder", customerOrder);
        return "dashboard/customerOrders/customerOrdersCreate";
    }

    @PostMapping("/customerOrder/create")
    public String createCustomerOrderPost(@ModelAttribute("customerOrder") CustomerOrder customerOrder, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        customerOrderDAO.insertCustomerOrder(customerOrder);
        return "redirect:/customerOrder/";
    }

    @GetMapping ("/customerOrderItem/create")
    public String createCustomerOrderItem(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<CustomerOrder>customerOrder = customerOrderDAO.getAllCustomerOrders();
        List<Product>product = productDAO.getAllProduct();
        model.addAttribute("customerOrder" , customerOrder);
        model.addAttribute("product" , product);
        CustomerOrderItem customerOrderItem = new CustomerOrderItem();
        model.addAttribute("customerOrderItem", customerOrderItem);
        return "dashboard/customerOrders/customerOrderItemsCreate";
    }

    @PostMapping("/customerOrderItem/create")
    public String createCustomerOrderItemPost(@ModelAttribute("customerOrderItem") CustomerOrderItem customerOrderItem, HttpSession session){
        customerOrderItemDAO.insertCustomerOrderItem(customerOrderItem);
        int id = customerOrderItem.getOrderID();
        System.out.println(id);
        List<InventoryItem>items = customerOrderItemDAO.updateOrderIDInInventory(customerOrderItem.getProductID());
        for(int i=0;i<customerOrderItem.getQuantity();i++){
//            System.out.println("Hello" + items.get(i).getItemID());
            inventoryItemDAO.updateOrderID(items.get(i).getItemID() , id);
        }
        Product product = productDAO.getProductByID(customerOrderItem.getProductID());
        int x = product.getAmountInStock() - customerOrderItem.getQuantity();
        product.setAmountInStock(x);
        productDAO.updateProduct(product.getProductID() , product);
        return "redirect:/customerOrder/";
    }

    @GetMapping ("/customerOrders/edit/{id}")
    public String editCustomerOrder(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Customer>customer = customerDAO.getAllCustomer();
        model.addAttribute("employee" , employee);
        model.addAttribute("customer" , customer);
        CustomerOrder customerOrder = customerOrderDAO.getCustomerOrderByID(id);
        model.addAttribute("customerOrder", customerOrder);
        return "dashboard/customerOrders/customerOrdersEdit";
    }

    @PostMapping ("/customerOrders/edit/{id}")
    public String editCustomerOrderPost(@PathVariable("id") int id, @ModelAttribute("customerOrder") CustomerOrder customerOrder, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        customerOrderDAO.updateCustomerOrder(id, customerOrder);
        return "redirect:/customerOrder/";
    }

    @GetMapping("/customerOrders/view/{id}")
    public String searchCustomerOrder(@PathVariable("id") int id,Model model, HttpSession session) {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<CustomerOrderItem> items = customerOrderItemDAO.getCustomerOrderItemByCustomerOrder(id);
        model.addAttribute("items" , items);
        return "dashboard/customerOrders/customerOrderSearch";
    }
}
