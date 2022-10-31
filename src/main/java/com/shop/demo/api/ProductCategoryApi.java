package com.shop.demo.api;

import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import com.shop.demo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productCategory")
public class ProductCategoryApi {

    private ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryApi(ProductCategoryService productCategoryService){
        this.productCategoryService=productCategoryService;
    }

    @GetMapping(path = "category/{category}")
    public List<Product> getAllProductByCategory(@PathVariable("category") String category) {
        return productCategoryService.getAllProductByCategory(category);
    }

    @PostMapping
    public int insertProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.insertProductCategory(productCategory);
    }

    @DeleteMapping(path = "{name}")
    public int deleteProductCategory(@PathVariable("name") String name) {
        return productCategoryService.deleteProductCategory(name);
    }

    @GetMapping(path = "{name}")
    public String getProductCategory(@PathVariable("name") String name) {
        return productCategoryService.getProductCategory(name);
    }

    @GetMapping
    public List<ProductCategory> getAllProductCategory(){ return productCategoryService.getAllProductCategory();}
}
