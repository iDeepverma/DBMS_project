package com.shop.demo.appController;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.dao.SupplyOrderDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Supplier;
import com.shop.demo.model.SupplyOrder;
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
}
