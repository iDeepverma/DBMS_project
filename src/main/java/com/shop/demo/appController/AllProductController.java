package com.shop.demo.appController;

import com.shop.demo.dao.ProductCategoryDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AllProductController {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;
    @GetMapping("/allProducts")
    public String homepage(Model model, HttpSession session){

        List<Product> products =productDAO.getAllProduct();
        model.addAttribute( "products",products);
        List<ProductCategory> categories = productCategoryDAO.getAllProductCategory();
        model.addAttribute("categories", categories);
        return "allProducts";
    }
    @GetMapping("allProducts/{category}")
    public String allProductsofCategories(@PathVariable("category") String category, Model model, HttpSession session){
        List<Product>productsbycategory = productCategoryDAO.getAllProductByCategory(category);
        model.addAttribute("products",productsbycategory);
        List<ProductCategory> categories = productCategoryDAO.getAllProductCategory();
        model.addAttribute("categories", categories);
        return "allProducts";
    }
}
