package com.shop.demo.dao;

import com.shop.demo.model.CustomerOrder;

public interface CustomerOrderDAO {
    int insertCustomerOrder(CustomerOrder customerOrder);
    int deleteCustomerOrder(int id);
    int updateCustomerOrder(int id, CustomerOrder customerOrder);
    int getCustomerOrderByID(int id);
}
