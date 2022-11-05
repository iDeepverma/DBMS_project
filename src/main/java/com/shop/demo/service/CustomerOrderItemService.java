package com.shop.demo.service;

import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.dao.CustomerOrderItemDAO;
import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.CustomerOrderItem;
import com.shop.demo.model.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerOrderItemService {
    private CustomerOrderItemDAO customerOrderItemDAO;

    @Autowired
    public CustomerOrderItemService(@Qualifier("customerOrderItem_mysql_repo") CustomerOrderItemDAO customerOrderItemDAO) {
        this.customerOrderItemDAO = customerOrderItemDAO;
    }
    public int insertCustomerOrderItem(CustomerOrderItem customerOrderItem) {
        return customerOrderItemDAO.insertCustomerOrderItem(customerOrderItem);
    }

    public int deleteCustomerOrderItem(int id, int productID) {
        return customerOrderItemDAO.deleteCustomerOrderItem(id, productID);
    }

    public List<CustomerOrderItem> getCustomerOrderItemByCustomerOrder(int id) {
        return customerOrderItemDAO.getCustomerOrderItemByCustomerOrder(id);
    }

    public int getProfitPerOrderItem(CustomerOrderItem customerOrderItem) {
        return customerOrderItemDAO.getProfitPerOrderItem(customerOrderItem);
    }

    public List<CustomerOrderItem> getAllCustomerOrderItem() {
        return customerOrderItemDAO.getAllCustomerOrderItem();
    }

    public List<InventoryItem> updateOrderIDInInventory(int productID){
        return customerOrderItemDAO.updateOrderIDInInventory(productID);
    }
}
