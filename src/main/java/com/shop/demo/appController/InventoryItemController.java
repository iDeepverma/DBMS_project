package com.shop.demo.appController;

import com.shop.demo.dao.*;
import com.shop.demo.model.*;
import com.shop.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
    private SupplyOrderDAO supplyOrderDAO;
    @Autowired
    private InventoryItemDAO inventoryItemDAO;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/inventoryItem")
    public String inventoryItems(Model model, HttpSession session){
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        List<InventoryItem> items = inventoryItemDAO.getAllInventoryItems();
        model.addAttribute("items",items);
        return "dashboard/inventoryItem/inventoryItem";
    }

    @GetMapping ("/inventoryItem/create")
    public String createInventoryItem(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        if(!authenticationService.isAdmin(session)){
            System.out.println("Non admin trying to Access Employee page");
            return "redirect:/error";
        }

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
        if(!authenticationService.isAdmin(session)){
            System.out.println("Non admin trying to Access Employee page");
            return "redirect:/error";
        }


        inventoryItemDAO.insertItem(InventoryItem);
        return "redirect:/inventoryItem/";
    }
}
