package com.shop.demo.appController;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Supplier;
import com.shop.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class SupplierController {
    @Autowired
    private SupplierDAO supplierDAO;

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/suppliers")
    public String getAllsuppliers(Model model, HttpSession session){

        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        List<Supplier> suppliers = supplierDAO.getAllSupplier();
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
        model.addAttribute("suppliers",suppliers);
        return "dashboard/supplier/suppliers.html";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        Supplier suppliers = supplierDAO.getSupplierByID(id);
        model.addAttribute("suppliers", suppliers);
        return "dashboard/supplier/suppliersEdit.html";
    }

    @PostMapping ("/suppliers/edit/{id}")
    public String editSupplierPost(@PathVariable("id") int id, @ModelAttribute("supplier") Supplier supplier, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        supplierDAO.updateSupplier(id , supplier);
        return "redirect:/suppliers/";
    }

    @GetMapping ("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable("id") int id, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        supplierDAO.deleteSupplier(id);
        return "redirect:/suppliers/";
    }

    @PostMapping ("/suppliers/create")
    public String createSupplierPost(@ModelAttribute("supplier") Supplier supplier, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        supplierDAO.insertSupplier(supplier);
        return "redirect:/suppliers/";
    }

    @GetMapping ("/suppliers/create")
    public String createSupplier(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "dashboard/supplier/supplierCreate.html";
    }

}
