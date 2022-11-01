package com.shop.demo.appController;

import com.shop.demo.dao.ProductCategoryDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductDAO dao1;

    @Autowired
    private ProductCategoryDAO dao2;

    @GetMapping("/getproduct")
    public String getProduct(Model model, @RequestParam HashMap<String, String> param) {
        Product product = dao1.getProductByID(Integer.parseInt(param.get("id")));
        model.addAttribute("product", product);
        return "test";
    }
}
