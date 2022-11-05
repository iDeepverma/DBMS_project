package com.shop.demo.dao;

import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.CustomerOrderItem;
import com.shop.demo.model.InventoryItem;

import java.util.List;

public interface CustomerOrderItemDAO {
    int insertCustomerOrderItem(CustomerOrderItem customerOrderItem);
    //INSERT INTO CustomerOrderItem VALUES (customerOrderItem);
    //productid also needed
    int deleteCustomerOrderItem(int id,int productID);
    //DELETE FROM CustomerOrderItem WHERE orderID=id,CustomerOrderItem.productID=productID;
    List<CustomerOrderItem> getCustomerOrderItemByCustomerOrder(int id);
    //SELECT * FROM CustomerOrderItem WHERE CustomerOrderItem.orderID = customerOrder.orderID;
    int getProfitPerOrderItem(CustomerOrderItem customerOrderItem);

    List<CustomerOrderItem> getAllCustomerOrderItem();
    //SELECT (customerOrderItem.quantity*(customerOrderItem.sellingPrice-Product.costPrice)) as profitperorderitem FROM Product WHERE customerOrderItem.productID=Product.productID;
    List<InventoryItem>updateOrderIDInInventory(int productID);
}