package com.shop.demo.appController;

import com.shop.demo.dao.InventoryItemDAO;
import com.shop.demo.model.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

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
    private InventoryItemDAO inventoryItemDAO;

    @GetMapping("inventoryItem")
    public String inventoryItems(Model model){
        List<InventoryItem> items = inventoryItemDAO.getAllInventoryItems();
        model.addAttribute("items",items);
        return "dashboard/inventoryItem/inventoryItem";
    }
}
