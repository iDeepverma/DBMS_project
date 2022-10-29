package com.shop.demo.repository;

import com.shop.demo.dao.InventoryItemDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.InventoryItem;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("mysql_repo")

public class InventoryItemMysql implements InventoryItemDAO {

//    itemID int AUTO_INCREMENT,
//    productID int,
//    supplyOrderID int,
//    orderID int,

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertItem(InventoryItem inventoryItem) {
        String query="INSERT INTO InventoryItem(itemID,productID,supplyOrderID,orderID) VALUES (?,?,?,?);";
        Object[] args=new Object[]{
          inventoryItem.getItemID(),
          inventoryItem.getProduct(),
          inventoryItem.getSupplyOrder(),
          inventoryItem.getCustomerOrderItem()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteItem(int itemID, int productID) {
        String query = "DELETE FROM InventoryItem WHERE itemID=? AND productID=?;";
        Object[] args = new Object[]{
                itemID,productID
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public InventoryItem getItemByProduct(int product) {
        String query ="select * from InventoryItem where productID=?;";
        Object[] args=new Object[]{
                product
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(InventoryItem.class));
    }

    @Override
    public int getStock(Product product) {
        String query="SELECT amountInStock FROM Product WHERE Product.productID=?; ";
        Object[] args =new Object[]{
                product.getProductID()
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Integer.class));
    }

    @Override
    public int markItemSold(int itemID, int product) {
        String query="UPDATE PRODUCT SET amountInStock = amountInStock-1 WHERE InventoryItem.itemID =? AND InventoryItem.productID = ?;";
        Object[] args=new Object[]{

          itemID,product
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateItem(int itemID, int productID, InventoryItem inventoryItem) {
        String query =" UPDATE InventoryItem SET supplyOrderID=?, orderID=?  WHERE InventoryItem.itemID=? AND InventoryItem.productID=?;";
        Object[] args=new Object[]{
                inventoryItem.getSupplyOrder(),
                inventoryItem.getCustomerOrderItem(),
        };
        return jdbcTemplate.update(query ,args);
    }
}
