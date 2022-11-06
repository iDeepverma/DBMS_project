package com.shop.demo.appController;

import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.Supplier;
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

    @GetMapping("/suppliers")
    public String getAllsuppliers(Model model, HttpSession session){
        List<Supplier> suppliers = supplierDAO.getAllSupplier();
        model.addAttribute("suppliers",suppliers);
        return "dashboard/supplier/suppliers.html";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model, HttpSession session)
    {
        Supplier suppliers = supplierDAO.getSupplierByID(id);
        model.addAttribute("suppliers", suppliers);
        return "dashboard/supplier/suppliersEdit.html";
    }

    @PostMapping ("/suppliers/edit/{id}")
    public String editSupplierPost(@PathVariable("id") int id, @ModelAttribute("supplier") Supplier supplier, HttpSession session)
    {
        supplierDAO.updateSupplier(id , supplier);
        return "redirect:/suppliers/";
    }

    @GetMapping ("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable("id") int id, HttpSession session)
    {
        supplierDAO.deleteSupplier(id);
        return "redirect:/suppliers/";
    }

    @PostMapping ("/suppliers/create")
    public String createSupplierPost(@ModelAttribute("supplier") Supplier supplier, HttpSession session)
    {
        supplierDAO.insertSupplier(supplier);
        return "redirect:/suppliers/";
    }

    @GetMapping ("/suppliers/create")
    public String createSupplier(Model model, HttpSession session)
    {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "dashboard/supplier/supplierCreate.html";
    }

}
