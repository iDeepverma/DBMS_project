package com.shop.demo.api;

import com.shop.demo.model.Product;
import com.shop.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductApi {

    private ProductService productService;

    @Autowired
    public ProductApi(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public int insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @GetMapping(path = "{id}")
    public Product getProductByID(@PathVariable("id") int id) {
        return productService.getProductByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateProduct(@PathVariable("id") int id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping(path = "{id}")
    public int deleteProduct(@PathVariable("id") int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping(path = "/quantity/{date}")
    public int getQuantityByProductByDate(@RequestBody Product product,@PathVariable("date") LocalDate startDate) {
        return productService.getQuantityByProductByDate(product, startDate);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
}
