package com.shop.demo.appController;

import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AllProductController {
    @Autowired
    private ProductDAO dao1;

    @GetMapping("/allProducts")
    public String homepage(Model model){

        List<Product> products =dao1.getAllProduct();
        model.addAttribute( "products",products);

        return "allProducts";
    }
}
