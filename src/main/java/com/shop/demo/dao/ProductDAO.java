package com.shop.demo.dao;

import com.shop.demo.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductDAO {
    int insertProduct(Product product);
//    INSERT INTO Product VALUES (product);

    Product getProductByID(int id);
//    select * from Product where productID=id;

    int updateProduct(int id, Product product);
//    UPDATE Product SET description=?, warrantyLenght=?,warrantyCoverage=?, MRP=?,costPrice=?,variant=?,amountInStock=? WHERE productID=id;

    int deleteProduct(int id);
//    DELETE FROM Product WHERE productID = id;

    int getQuantityByProductByDate(Product product, LocalDate startDate);

    List<Product> getAllProduct();
    //SELECT sum(quantity) FROM CustomerOrderItem, CustomerOrder WHERE product.productID = CustomerOrderItem.productID AND CustomerOrderItem.orderID = CustomerOrder.orderID AND CustomerOrder.orderDate >= startDate;


}
