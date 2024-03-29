package com.shop.demo.appController;

import com.shop.demo.FileUploadUtil;
import com.shop.demo.dao.*;
import com.shop.demo.model.*;
import com.shop.demo.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private SupplierDAO supplierDAO;
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
    public String listProducts(Model model, HttpSession session, @RequestParam(required = false) String id)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        List<Product>best = productDAO.bestProducts();
        model.addAttribute("best" , best);
        List<String>category = productCategoryDAO.getAllCategory();
        List<Long>profit = new ArrayList<>();
        List<Long>revenue = new ArrayList<>();
        List<String>Category = new ArrayList<>();
        int temp = 0;
        for(int i=0;i<category.size();i++){
            long totalrevenue = 0,totalprofit = 0;
            String eachcat = category.get(i);
            List<CustomerOrderItem>items = customerOrderItemDAO.getAllCustomerOrderItem();
            List<Product> products = productCategoryDAO.getAllProductByCategory(eachcat);
//            for(int j=0;j<items.size();j++){
//                CustomerOrderItem order = items.get(j);
//                for(int k=0;k<products.size();k++){
//                    if((products.get(k).getProductID()) == items.get(j).getProductID()){
//                        totalprofit = totalprofit + (items.get(j).getQuantity())*(items.get(j).getSellingPrice() - products.get(k).getCostPrice()) ;
//                        totalrevenue = totalrevenue + (items.get(j).getQuantity()*items.get(j).getSellingPrice());
//                    }
//                }
//            }
            profit.add(productCategoryDAO.profitByCategory(eachcat));
            revenue.add(productCategoryDAO.revenueByCategory(eachcat));
            Category.add(eachcat);
        }
        List<Product>product = new ArrayList<>();
        if(id == null || id == "") {
            product.addAll(productDAO.getAllProduct());
        }
        else{
            int intid = Integer.parseInt(id);
            System.out.println(intid);
            try {
                Product item = productDAO.getProductByID(intid);
                product.add(item);
            }
            catch (Exception e) {
                System.out.println("ITEM NOT FOUND!");
//                System.out.println(e);
            }
        }
        model.addAttribute("product",product);
        model.addAttribute("profit",profit);
        model.addAttribute("revenue",revenue);
        model.addAttribute("Category",Category);
        List<Product>temp1 = productDAO.getAllProduct();

        int totalCust = customerOrderDAO.getAllCustomerOrders().size();
        int totalProd = productDAO.getAllProduct().size();
        int totalSales = customerOrderDAO.getTotalSales();
        int totalSuppliers = supplierDAO.getAllSupplier().size();
        model.addAttribute("totalCust", totalCust);
        model.addAttribute("totalProd", totalProd);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalSuppliers", totalSuppliers);
        model.addAttribute("temp1",temp1);
        return "dashboard/product/productList";
    }

    @GetMapping ("/product/create")
    public String createProduct(Model model, HttpSession session)
    {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        ProductComplex productComplex = new ProductComplex();
        List<ProductCategory>categories = productCategoryDAO.getAllProductCategory();
        List<String> categoryName = productCategoryDAO.getAllCategory();

        for(int i=0; i<categoryName.size(); i++){
            System.out.println(categoryName.get(i));
        }
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categories",categories);
        model.addAttribute("productComplex", productComplex);
        return "dashboard/product/productCreate.html";
    }


    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute("product") ProductComplex productComplex, @RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {
        if(!authenticationService.isAuthenticated(session)){
            return "redirect:/login";
        }
        Product product = productComplex.getProduct();
        ProductCategory productCategory = productComplex.getProductCategory();
        productCategory.setName(product.getName());

        productCategoryDAO.insertProductCategory(productCategory);
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
