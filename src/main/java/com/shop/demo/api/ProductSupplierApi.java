package com.shop.demo.api;


import com.shop.demo.model.Product;
import com.shop.demo.model.ProductSuppliers;
import com.shop.demo.model.Supplier;
import com.shop.demo.service.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productSupplier")
public class ProductSupplierApi {

    private ProductSupplierService productSupplierService;

    @Autowired
    public ProductSupplierApi(ProductSupplierService productSupplierService){
        this.productSupplierService=productSupplierService;
    }

    @PostMapping
    public int insertProductSuppliers( @RequestBody ProductSuppliers productSuppliers) {
        return productSupplierService.insertProductSuppliers(productSuppliers);
    }

//    Check
//    @DeleteMapping()
//    public int deleteProductSuppliers(Product product, Supplier supplier) {
//        return productSupplierService.deleteProductSuppliers(product , supplier);
//    }

    @GetMapping(path = "productOf/{id}")
    public ProductSuppliers getProductsOfSuppliers(@PathVariable("id") int supplierID) {
        return productSupplierService.getProductsOfSuppliers(supplierID);
    }

    @GetMapping(path = "supplierOf/{id}")
    public ProductSuppliers getSuppliersOfProduct(@PathVariable("id") int productID) {
        return productSupplierService.getSuppliersOfProduct(productID);
    }

    @GetMapping
    public List<ProductSuppliers> getAllProductSuppliers(){return productSupplierService.getAllProductSuppliers();}
}
