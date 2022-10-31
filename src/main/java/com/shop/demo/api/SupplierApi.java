package com.shop.demo.api;

import com.shop.demo.model.Supplier;
import com.shop.demo.service.EmployeeService;
import com.shop.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("api/v1/supplier")
public class SupplierApi {
    private SupplierService supplierService;

    @Autowired
    public SupplierApi(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @PostMapping
    public int insertSupplier(@RequestBody Supplier supplier){
        return supplierService.insertSupplier(supplier);
    }

    @GetMapping
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }


    @GetMapping(path  = "{id}")
    public Supplier getSupplierByID(@PathVariable("id") int id){
        return supplierService.getSupplierByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateSupplier(@PathVariable("id") int id, @RequestBody Supplier supplier){
        return supplierService.updateSupplier(id,supplier);
    }

    @DeleteMapping(path = "{id}")
    public int deleteSupplier(@PathVariable("id") int id){
        return supplierService.deleteSupplier(id);
    }

    @GetMapping(path = "money/")
    public int updateMoney(@RequestParam HashMap<String,String> param){
        return supplierService.updateMoney(Integer.parseInt(param.get("id")), Integer.parseInt(param.get("money")));
    }

    @GetMapping(path = "update/")
    public int updateOrdersFullfilled(@RequestParam HashMap<String,String> param){
//        System.out.print("$$$$$"+param.get("id")+" "+param.get("orders"));
        return supplierService.updateOrdersFullfilled(Integer.parseInt(param.get("id")),Integer.parseInt(param.get("orders")));
    }

}

























