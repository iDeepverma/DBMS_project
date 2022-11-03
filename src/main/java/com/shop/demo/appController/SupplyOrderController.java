package com.shop.demo.appController;

import com.shop.demo.dao.*;
import com.shop.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/supplyOrders")
    public String listSupplyOrders(Model model){
        List<SupplyOrder> supplyOrders = supplyOrderDAO.getAllSupplyOrders();
        model.addAttribute("supplyOrders" , supplyOrders);
        return "dashboard/supplyOrders/supplyOrders";
    }

    @GetMapping ("/supplyOrders/create")
    public String createSupplyOrders(Model model)
    {
        SupplyOrder supplyOrder = new SupplyOrder();
        model.addAttribute("supplyOrder", supplyOrder);
        List<Employee>employee = employeeDAO.getAllEmployee();
        List<Supplier>supplier = supplierDAO.getAllSupplier();
        model.addAttribute("supplier" , supplier);
        model.addAttribute("employee" , employee);
        return "dashboard/supplyOrders/supplyOrdersCreate";
    }

    @PostMapping("/supplyOrders/create")
    public String createsupplyOrdersPost(@ModelAttribute("SupplyOrder") SupplyOrder SupplyOrder)
    {
        supplyOrderDAO.insertSupplyOrder(SupplyOrder);
        return "redirect:/supplyOrders/";
    }
    @GetMapping ("/supplyOrderItem/create")
    public String createSupplyOrderItems(Model model)
    {
        SupplyOrderItem supplyOrderItem = new SupplyOrderItem();
        model.addAttribute("supplyOrderItem", supplyOrderItem);
        List<SupplyOrder>supplyOrder = supplyOrderDAO.getAllSupplyOrders();
        List<Product>product = productDAO.getAllProduct();
        model.addAttribute("supplyOrder" , supplyOrder);
        model.addAttribute("product" , product);
        return "dashboard/supplyOrders/supplyOrderItemCreate";
    }

    @PostMapping("/supplyOrderItem/create")
    public String createsupplyOrderItemsPost(@ModelAttribute("supplyOrderItem") SupplyOrderItem supplyOrderItem)
    {
        supplyOrderItemDAO.insertSupplyOrderItem(supplyOrderItem);
        int quantity = supplyOrderItem.getQuantity();
        for(int i=0;i<quantity;i++){
            InventoryItem item = new InventoryItem();
            item.setSupplyOrderID(supplyOrderItem.getSupplyOrderID());
            item.setProductID(supplyOrderItem.getProductID());
            inventoryItemDAO.insertItemUnsold(item);
        }
        return "redirect:/supplyOrders/";
    }
}
