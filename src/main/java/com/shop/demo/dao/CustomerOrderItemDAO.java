package com.shop.demo.dao;

import com.shop.demo.model.CustomerOrderItem;

public interface CustomerOrderItemDAO {
    int insertCustomerOrderItem(CustomerOrderItem customerOrderItem);
    int deleteCustomerOrderItem(int id);
}
