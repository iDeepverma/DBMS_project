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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@Controller
public class ProductController {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @GetMapping("/getproduct")
    public String getProduct(Model model, @RequestParam HashMap<String, String> param) {
        Product product = productDAO.getProductByID(Integer.parseInt(param.get("id")));
        model.addAttribute("product", product);
        return "test";
    }

    @GetMapping("/productDetail/{id}")
    public String individualProductDetails(@PathVariable("id") int id , Model model){
        Product products = productDAO.getProductByID(id);
        model.addAttribute("product",products);

        List<ProductCategory> categories = productCategoryDAO.getAllProductCategory();
        model.addAttribute("categories", categories);

        return "productDetail";
    }

    @GetMapping("/productByCategory")
    public String productByCategory(Model model){
        List<List<Product>>productCategorri = new ArrayList<>();
        List<ProductCategory>category;
        category = productCategoryDAO.getAllProductCategory();
        for(int i=0;i<category.size();i++){
            List<Product>temp =new Vector<Product>();
            temp = productCategoryDAO.getAllProductByCategory(category.get(i).getCategory());
            for(int j=0;j<temp.size();j++){
                System.out.println(temp.get(j).getProductID()+ " ");
            }
            System.out.println(category.get(i).getCategory());
            productCategorri.add(temp);
        }
        model.addAttribute("productCategorri",productCategorri);
        model.addAttribute("category", category);
        return "test";
    }

    @GetMapping("/stockAvailability")
    public String stockAvailability(Model model){
        System.out.println("stock Avaialability called");
        List<Product>temp = productDAO.getAllProduct();
        List<String>category = new ArrayList<>();
        List<Integer>stocks = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            System.out.println(temp.get(i).getName());
            category.add(temp.get(i).getName());
            stocks.add(temp.get(i).getAmountInStock());
        }
        model.addAttribute("names",category);
        model.addAttribute("stocks",stocks);
        return "stocksAvailability";
    }
}
