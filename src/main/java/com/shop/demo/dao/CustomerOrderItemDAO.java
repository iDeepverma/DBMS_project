package com.shop.demo.dao;

import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.CustomerOrderItem;
import com.shop.demo.model.Product;

import java.util.List;

public interface CustomerOrderItemDAO {
    int insertCustomerOrderItem(CustomerOrderItem customerOrderItem);
    int deleteCustomerOrderItem(int id);
    List<CustomerOrderItem> getCustomerOrderItemByCustomerOrder(CustomerOrder customerOrder);

    int getProfitPerOrderItem(CustomerOrderItem customerOrderItem);
}