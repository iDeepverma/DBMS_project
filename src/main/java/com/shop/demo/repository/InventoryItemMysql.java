package com.shop.demo.repository;

import com.shop.demo.dao.InventoryItemDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.InventoryItem;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("inventoryItem_mysql_repo")

public class InventoryItemMysql implements InventoryItemDAO {

//    itemID int AUTO_INCREMENT,
//    productID int,
//    supplyOrderID int,
//    orderID int,

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertItem(InventoryItem inventoryItem) {
        String query="INSERT INTO InventoryItem(productID,supplyOrderID,orderID) VALUES (?,?,?);";
        Object[] args=new Object[]{
          inventoryItem.getProductID(),
          inventoryItem.getSupplyOrderID(),
          inventoryItem.getOrderID()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteItem(int itemID) {
        String query = "DELETE FROM InventoryItem WHERE itemID=?;";
        Object[] args = new Object[]{
                itemID
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<InventoryItem> getItemByProduct(int product) {
        String query ="select * from InventoryItem where productID=?;";
        Object[] args=new Object[]{
                product
        };
        return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(InventoryItem.class));
    }





    @Override
    public int updateItem(int itemID,  InventoryItem inventoryItem) {
        String query =" UPDATE InventoryItem SET supplyOrderID=?, orderID=?  WHERE InventoryItem.itemID=?;";
        Object[] args=new Object[]{
                inventoryItem.getSupplyOrderID().getOrderID(),
                inventoryItem.getOrderID().getOrderID(),
                itemID
        };
        return jdbcTemplate.update(query ,args);
    }
}
