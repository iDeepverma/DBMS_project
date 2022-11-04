package com.shop.demo.appController;

import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class SupplierController {
    @Autowired
    private SupplierDAO supplierDAO;

    @GetMapping("/suppliers")
    public String getAllsuppliers(Model model){
        List<Supplier> suppliers = supplierDAO.getAllSupplier();
        model.addAttribute("suppliers",suppliers);
        return "dashboard/supplier/suppliers.html";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model)
    {
        Supplier suppliers = supplierDAO.getSupplierByID(id);
        model.addAttribute("suppliers", suppliers);
        return "dashboard/supplier/suppliersEdit.html";
    }

    @PostMapping ("/suppliers/edit/{id}")
    public String editSupplierPost(@PathVariable("id") int id, @ModelAttribute("supplier") Supplier supplier)
    {
        supplierDAO.updateSupplier(id , supplier);
        return "redirect:/suppliers/";
    }

    @GetMapping ("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable("id") int id)
    {
        supplierDAO.deleteSupplier(id);
        return "redirect:/suppliers/";
    }

    @PostMapping ("/suppliers/create")
    public String createSupplierPost(@ModelAttribute("supplier") Supplier supplier)
    {
        supplierDAO.insertSupplier(supplier);
        return "redirect:/suppliers/";
    }

    @GetMapping ("/suppliers/create")
    public String createSupplier(Model model)
    {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "dashboard/supplier/supplierCreate.html";
    }

}
