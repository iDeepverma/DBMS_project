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
import java.util.Date;
import java.util.List;

@Controller
public class InventoryItemController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private SupplierDAO supplierDAO;
    @Autowired
    private SupplyOrderDAO supplyOrderDAO;
    @Autowired
    private InventoryItemDAO inventoryItemDAO;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/inventoryItem")
    public String inventoryItems(Model model, HttpSession session, @RequestParam(required = false) String id){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        
        
        List<InventoryItem> items = new ArrayList<>();
        if (id == null || id == "") {
//            System.out.println(inventoryItemDAO.getAllInventoryItems());
//            List<InventoryItem> items = inventoryItemDAO.getAllInventoryItems();
            items.addAll(inventoryItemDAO.getAllInventoryItems());
        }
        else {
            int intid = Integer.parseInt(id);
            try {
                InventoryItem item = inventoryItemDAO.getInventoryItemByID(intid);
                items.add(item);
            }
            catch (Exception e) {
                System.out.println("ITEM NOT FOUND!");
//                System.out.println(e);
            }
        }
        int totalCust = customerOrderDAO.getAllCustomerOrders().size();
        int totalProd = productDAO.getAllProduct().size();
        int totalSales = 0;
        List<CustomerOrder> totSales = customerOrderDAO.getAllCustomerOrders();
        for(int i=0;i<totSales.size();i++){
            totalSales = totalSales + totSales.get(i).getTotal();
        }
        int totalSuppliers = supplierDAO.getAllSupplier().size();
        model.addAttribute("totalCust", totalCust);
        model.addAttribute("totalProd", totalProd);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalSuppliers", totalSuppliers);
        model.addAttribute("items",items);
        System.out.println(items);
        return "dashboard/inventoryItem/inventoryItem";
    }

    @GetMapping ("/inventoryItem/create")
    public String createInventoryItem(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
//        if(!authenticationService.isAdmin(session)){
//            System.out.println("Non admin trying to Access Employee page");
//            return "redirect:/error";
//        }

        InventoryItem inventoryItem = new InventoryItem();
        model.addAttribute("inventoryItem",inventoryItem );
        List<SupplyOrder>supplyOrder = supplyOrderDAO.getAllSupplyOrders();
        List<CustomerOrder>customerOrder = customerOrderDAO.getAllCustomerOrders();
        List<Product>product = productDAO.getAllProduct();
        model.addAttribute("supplyOrder" , supplyOrder);
        model.addAttribute("customerOrder" , customerOrder);
        model.addAttribute("product" , product);
        return "dashboard/inventoryItem/inventoryItemCreate";
    }

    @PostMapping("/inventoryItem/create")
    public String createInventoryItemPost(@ModelAttribute("inventoryItem") InventoryItem InventoryItem, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
//        if(!authenticationService.isAdmin(session)){
//            System.out.println("Non admin trying to Access Employee page");
//            return "redirect:/error";
//        }


        inventoryItemDAO.insertItem(InventoryItem);
        return "redirect:/inventoryItem/";
    }
}
