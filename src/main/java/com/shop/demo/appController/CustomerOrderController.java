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
    private SupplierDAO supplierDAO;
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
    public String getAllcustomerOrders(Model model, HttpSession session, @RequestParam(required = false) String id){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<CustomerOrder> orders = new ArrayList<>();
        if (id == null || id == "") {
            orders = customerOrderDAO.getAllCustomerOrders();
        }
        else {
            int intid = Integer.parseInt(id);
            try {
                CustomerOrder item = customerOrderDAO.getCustomerOrderByID(intid);
                orders.add(item);
            }
            catch (Exception e) {
                System.out.println("ORDER NOT FOUND!");
//                System.out.println(e);
            }
        }
        int totalCust = customerOrderDAO.getAllCustomerOrders().size();
        int totalProd = productDAO.getAllProduct().size();
        int totalSales = customerOrderDAO.getTotalSales();
        int totalSuppliers = supplierDAO.getAllSupplier().size();
        model.addAttribute("totalCust", totalCust);
        model.addAttribute("totalProd", totalProd);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalSuppliers", totalSuppliers);
        model.addAttribute("orders", orders);
        return "dashboard/customerOrders/customerOrders.html";
    }

    @GetMapping ("/customerOrder/create")
    public String createCustomerOrder(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        int id = authenticationService.getCurrentUser(session);
        model.addAttribute("employee" , employeeDAO.getEmployeeByID(id));
//        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Customer>customer = customerDAO.getAllCustomer();
//        model.addAttribute("employee" , employee);
        model.addAttribute("customer" , customer);
        CustomerOrder customerOrder = new CustomerOrder();
//       current date
        customerOrder.setOrderDate(new java.util.Date());
        model.addAttribute("customerOrder", customerOrder);
        return "dashboard/customerOrders/customerOrdersCreate";
    }

    @PostMapping("/customerOrder/create")
    public String createCustomerOrderPost(@ModelAttribute("customerOrder") CustomerOrder customerOrder, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
//       setting date to current date
        customerOrder.setOrderDate(new java.util.Date());
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

        CustomerOrder customerOrder = customerOrderDAO.getCustomerOrderByID(customerOrderItem.getOrderID());
        customerOrder.setTotal(customerOrder.getTotal()+customerOrderItem.getSellingPrice()*customerOrderItem.getQuantity());
        customerOrderDAO.updateCustomerOrder(customerOrder.getOrderID(),customerOrder);

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
        int uid = authenticationService.getCurrentUser(session);
        model.addAttribute("employee" , employeeDAO.getEmployeeByID(uid));

//        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Customer>customer = customerDAO.getAllCustomer();
//        model.addAttribute("employee" , employee);
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
        CustomerOrder order = customerOrderDAO.getCustomerOrderByID(id);
        model.addAttribute("order", order);


        int totalCust = customerOrderDAO.getAllCustomerOrders().size();
        int totalProd = productDAO.getAllProduct().size();
        int totalSales = customerOrderDAO.getTotalSales();
        int totalSuppliers = supplierDAO.getAllSupplier().size();
        model.addAttribute("totalCust", totalCust);
        model.addAttribute("totalProd", totalProd);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalSuppliers", totalSuppliers);

        return "dashboard/customerOrders/customerOrderSearch";
    }

    @GetMapping("/customerOrders/view/{id}/invoice")
    public String customerOrderInvoice(@PathVariable("id") int id, Model model, HttpSession session) {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        CustomerOrder order = customerOrderDAO.getCustomerOrderByID(id);
        List<CustomerOrderItemProduct> items =  new ArrayList<>();
        List<CustomerOrderItem> citems = customerOrderItemDAO.getCustomerOrderItemByCustomerOrder(id);
        for (CustomerOrderItem customerOrderItem : citems) {
            CustomerOrderItemProduct coip = new CustomerOrderItemProduct();
            coip.setItem(customerOrderItem);
            int pid = customerOrderItem.getProductID();
            Product prod = productDAO.getProductByID(pid);
            coip.setProduct(prod);
            items.add(coip);
        }

        Customer customer = customerDAO.getCustomerByID(order.getCustomerID());
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        model.addAttribute("customer", customer);

        return "dashboard/customerOrders/invoice";
    }
}
