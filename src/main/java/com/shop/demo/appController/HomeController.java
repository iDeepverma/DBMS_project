package com.shop.demo.appController;

import com.shop.demo.dao.ProductCategoryDAO;
import com.shop.demo.dao.ProductDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @GetMapping({"/","/home"})
    public String homepage(Model model, HttpSession session){

        List<Product> products =productDAO.getAllProduct();
        model.addAttribute( "products",products);

        List<String> categories =productCategoryDAO.getAllCategory();
        model.addAttribute( "cats",categories);

        return "home";
    }
}
