package com.shop.demo.repository;

import com.shop.demo.dao.CustomerOrderItemDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.CustomerOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("customerOrderItem_mysql_repo")
public class CustomerOrderItemMysql implements CustomerOrderItemDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int insertCustomerOrderItem(CustomerOrderItem customerOrderItem) {
        String query = "INSERT INTO CustomerOrderItem(orderID,quantity,sellingPrice,productID,additionInfo) VALUES (?,?,?,?,?);";
        Object[] args = new Object[] {
          customerOrderItem.getOrderID(),
          customerOrderItem.getQuantity(),
          customerOrderItem.getSellingPrice(),
          customerOrderItem.getProductID(),
          customerOrderItem.getAdditionalInfo()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteCustomerOrderItem(int id, int productID) {
        String query = "DELETE FROM CustomerOrderItem WHERE orderID=?,CustomerOrderItem.productID=?;";
        Object[] args = new Object[]{
                id,
                productID
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<CustomerOrderItem> getCustomerOrderItemByCustomerOrder(CustomerOrder customerOrder) {
        String query = "SELECT * FROM CustomerOrderItem WHERE CustomerOrderItem.orderID = ?;";
        Object[] args = new Object[]{
                customerOrder.getOrderID()
        };
        return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(CustomerOrderItem.class));
    }

    @Override
    public int getProfitPerOrderItem(CustomerOrderItem customerOrderItem) {
        String query = "SELECT (?*(?-Product.costPrice)) as profitperorderitem FROM Product WHERE Product.productID=?;";
        Object[] args = new Object[]{
                customerOrderItem.getQuantity(),
                customerOrderItem.getSellingPrice(),
                customerOrderItem.getProductID()
        };
        //discuss with deep as this query is giving error
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Integer.class));
    }

    @Override
    public List<CustomerOrderItem> getAllCustomerOrderItem(){
        String query = "select * from CustomerOrderItem ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(CustomerOrderItem.class));
    }
}
