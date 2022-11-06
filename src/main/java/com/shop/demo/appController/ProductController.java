package com.shop.demo.appController;

import com.shop.demo.FileUploadUtil;
import com.shop.demo.dao.CustomerOrderItemDAO;
import com.shop.demo.model.*;
import com.shop.demo.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import com.shop.demo.dao.ProductCategoryDAO;
import com.shop.demo.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomerOrderItemDAO customerOrderItemDAO;
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

            productCategorri.add(temp);
        }
        model.addAttribute("productCategorri",productCategorri);
        model.addAttribute("category", category);
        return "test";
    }

    @GetMapping ("/product")
    public String listProducts(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Product> product = productDAO.getAllProduct();
        List<Product>best = productDAO.bestProducts();
        model.addAttribute("best" , best);
        model.addAttribute("product", product);
        List<ProductCategory>category = productCategoryDAO.getAllProductCategory();
        List<Long>profit = new ArrayList<>();
        List<Long>revenue = new ArrayList<>();
        List<String>Category = new ArrayList<>();
        int temp = 0;
        for(int i=0;i<category.size();i++){
            long totalrevenue = 0,totalprofit = 0;
            String eachcat = category.get(i).getCategory();
            List<Product>temp1 = productCategoryDAO.getAllProductByCategory(eachcat);
            List<CustomerOrderItem>allitems = customerOrderItemDAO.getAllCustomerOrderItem();
            for(int j=0;j<temp1.size();j++){
                long id = temp1.get(j).getProductID();
                for(int k=0;k<allitems.size();k++){
                    if(allitems.get(k).getProductID() ==  id){
                        totalrevenue = totalrevenue + (allitems.get(k).getQuantity())*(allitems.get(k).getSellingPrice());
                        totalprofit = totalprofit + (allitems.get(k).getQuantity()*(allitems.get(k).getSellingPrice() - productDAO.getProductByID(allitems.get(i).getProductID()).getCostPrice()));
                    }
                }
            }
            profit.add(totalprofit);
            revenue.add(totalrevenue);
            Category.add(eachcat);
        }
        List<Product>temp1 = productDAO.getAllProduct();
        model.addAttribute("temp1",temp1);
        model.addAttribute("profit",profit);
        model.addAttribute("revenue",revenue);
        model.addAttribute("Category",Category);
        return "dashboard/product/productList";
    }

    @GetMapping ("/product/create")
    public String createProduct(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        Product product = new Product();
        List<ProductCategory>names = productCategoryDAO.getAllProductCategory();
        model.addAttribute("names",names);
        model.addAttribute("product", product);
        return "dashboard/product/productCreate.html";
    }


    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhotoPath(fileName);
        productDAO.insertProduct(product);
        FileUploadUtil.saveFile("product-photos/",fileName,multipartFile);
        return "redirect:/product/";
    }


    @GetMapping ("/productCategory/create")
    public String createProductCategory(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        ProductCategory productCategory = new ProductCategory();
        model.addAttribute("productCategory", productCategory);
        return "dashboard/product/productCategoryCreate.html";
    }


    @PostMapping("/productCategory/create")
    public String createProductCategoryPost(@ModelAttribute("productCategory") ProductCategory productCategory, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        productCategoryDAO.insertProductCategory(productCategory);
        return "redirect:/product/";
    }


    @GetMapping ("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        productDAO.deleteProduct(id);
        return "redirect:/product/";
    }

    @GetMapping ("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        Product product=productDAO.getProductByID(id);
        model.addAttribute("product", product);
        return "dashboard/product/productEdit.html";
    }

    @PostMapping ("/product/edit/{id}")
    public String editProductPost(@PathVariable("id") int id, @ModelAttribute("product") Product product, @RequestParam("image") MultipartFile multipartFile,HttpSession session) throws IOException {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(fileName!=null){
            product.setPhotoPath(fileName);
            FileUploadUtil.saveFile("product-photos/",fileName,multipartFile);
        }


        productDAO.updateProduct(id,product);
        return "redirect:/product/";
    }
}
