package com.shop.demo.model;

public class ProductComplex {
    private Product product;
    private ProductCategory productCategory;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductComplex(Product product, ProductCategory productCategory){
        this.product = product;
        this.productCategory = productCategory;
    }
}
