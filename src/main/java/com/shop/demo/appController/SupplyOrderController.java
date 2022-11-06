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
import java.util.Date;
import java.util.List;

@Controller
public class SupplyOrderController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SupplyOrderDAO supplyOrderDAO;

    @Autowired
    private SupplierDAO supplierDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private InventoryItemDAO inventoryItemDAO;

    @Autowired
    private SupplyOrderItemDAO supplyOrderItemDAO;
    @Autowired
    private AuthenticationService authenticationService;
    @GetMapping("/supplyOrders")
    public String listSupplyOrders(Model model, HttpSession session){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<SupplyOrder> supplyOrders = supplyOrderDAO.getAllSupplyOrders();
        model.addAttribute("supplyOrders" , supplyOrders);
        return "dashboard/supplyOrders/supplyOrders";
    }

    @GetMapping ("/supplyOrders/create")
    public String createSupplyOrders(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        SupplyOrder supplyOrder = new SupplyOrder();
        int id = authenticationService.getCurrentUser(session);
        model.addAttribute("supplyOrder", supplyOrder);
        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Supplier>supplier = supplierDAO.getAllSupplier();
        model.addAttribute("supplier" , supplier);
        model.addAttribute("employee" , employee);
        model.addAttribute("id" , id);
        return "dashboard/supplyOrders/supplyOrdersCreate";
    }

    @PostMapping("/supplyOrders/create")
    public String createsupplyOrdersPost(@ModelAttribute("SupplyOrder") SupplyOrder SupplyOrder, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        supplyOrderDAO.insertSupplyOrder(SupplyOrder);
        return "redirect:/supplyOrders/";
    }
    @GetMapping ("/supplyOrderItem/create")
    public String createSupplyOrderItems(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        SupplyOrderItem supplyOrderItem = new SupplyOrderItem();
        model.addAttribute("supplyOrderItem", supplyOrderItem);
        List<SupplyOrder>supplyOrder = supplyOrderDAO.getAllSupplyOrders();
        List<Product>product = productDAO.getAllProduct();
        model.addAttribute("supplyOrder" , supplyOrder);
        model.addAttribute("product" , product);
        return "dashboard/supplyOrders/supplyOrderItemCreate";
    }

    @PostMapping("/supplyOrderItem/create")
    public String createsupplyOrderItemsPost(@ModelAttribute("supplyOrderItem") SupplyOrderItem supplyOrderItem, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        supplyOrderItemDAO.insertSupplyOrderItem(supplyOrderItem);
        String status = supplyOrderDAO.getSupplyOrderByID(supplyOrderItem.getSupplyOrderID()).getDeliveryStatus();
        int quantity = supplyOrderItem.getQuantity();
        if(status.equals("Delivered")) {
            for (int i = 0; i < quantity; i++) {
                InventoryItem item = new InventoryItem();
                item.setSupplyOrderID(supplyOrderItem.getSupplyOrderID());
                item.setProductID(supplyOrderItem.getProductID());
                inventoryItemDAO.insertItemUnsold(item);
            }
            Product product = productDAO.getProductByID(supplyOrderItem.getProductID());
            int x = product.getAmountInStock();
            product.setAmountInStock(x + quantity);
            System.out.println(x);
            productDAO.updateProduct(product.getProductID() , product);
        }

        return "redirect:/supplyOrders/";
    }
    @GetMapping ("/supplyOrder/edit/{id}")
    public String editSupplyOrder(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        SupplyOrder supplyOrder = supplyOrderDAO.getSupplyOrderByID(id);
        model.addAttribute("supplyOrder", supplyOrder);
        return "dashboard/supplyOrders/SupplyOrderEdit.html";
    }

    @PostMapping ("/supplyOrder/edit/{id}")
    public String editSupplyOrderPost(@PathVariable("id") int id, @ModelAttribute("supplyOrder") SupplyOrder supplyOrder, HttpSession session){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        String finalStatus = supplyOrder.getDeliveryStatus();
        supplyOrderDAO.updateSupplyOrder(supplyOrder.getOrderID(), supplyOrder);
            List<SupplyOrderItem> temp = supplyOrderItemDAO.getAllSupplyOrderItems(supplyOrder.getOrderID());
            for (int i = 0; i < temp.size(); i++) {
                SupplyOrderItem x = temp.get(i);
                int quantity = x.getQuantity();
                for(int j=0;j<quantity;j++){
                    Product extra = new Product();
                    extra = productDAO.getProductByID(x.getProductID());
                    int amnt = extra.getAmountInStock() + quantity;
                    extra.setAmountInStock(amnt);
                    productDAO.updateProduct(x.getProductID() , extra);
                    InventoryItem item = new InventoryItem();
                    item.setSupplyOrderID(x.getSupplyOrderID());
                    item.setProductID(x.getProductID());
                    inventoryItemDAO.insertItemUnsold(item);
                }
            }
        return "redirect:/supplyOrders/";
    }

    @GetMapping ("/supplyOrder/search/{id}")
    public String searchSupplyOrderID(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<SupplyOrderItem> supplyOrderItem = supplyOrderItemDAO.getAllSupplyOrderItems(id);
        model.addAttribute("supplyOrderItem", supplyOrderItem);
        return "dashboard/supplyOrders/supplyOrderSearch.html";
    }

}
