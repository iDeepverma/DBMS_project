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
    public int insertItemUnsold(InventoryItem inventoryItem) {
//        String query="INSERT INTO InventoryItem(productID,supplyOrderID) VALUES (?,?);";
        String query="INSERT INTO InventoryItem(productID,supplyOrderID) VALUES (?,?);";
        Object[] args=new Object[]{
                inventoryItem.getProductID(),
                inventoryItem.getSupplyOrderID(),
        };
        return jdbcTemplate.update(query,args);
    }
    @Override
    public int updateOrderID(int itemID,int orderID){
//        String query="UPDATE InventoryItem SET orderID=? WHERE itemID=?;";
//        Object args = new Object[]{
//                orderID,
//                itemID
//        };
//        System.out.println("Hello "+ orderID + " "+ itemID);
        jdbcTemplate.execute("UPDATE InventoryItem SET orderID="+orderID+" WHERE itemID="+itemID+";");
        return 0;
//        return jdbcTemplate.update(query, args);
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


        BeanPropertyRowMapper<InventoryItem> inventoryItemBeanPropertyRowMapper = new BeanPropertyRowMapper<>(InventoryItem.class);
        inventoryItemBeanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        String query ="select * from InventoryItem where productID=?;";
        Object[] args=new Object[]{
                product
        };
        return jdbcTemplate.query(query,args, inventoryItemBeanPropertyRowMapper);
    }





    @Override
    public int updateItem(int itemID,  InventoryItem inventoryItem) {
        String query =" UPDATE InventoryItem SET supplyOrderID=?, orderID=?  WHERE InventoryItem.itemID=?;";
        Object[] args=new Object[]{
                inventoryItem.getSupplyOrderID(),
                inventoryItem.getOrderID(),
                itemID
        };
        return jdbcTemplate.update(query ,args);
    }
    @Override
    public List<InventoryItem> getAllInventoryItems(){

        BeanPropertyRowMapper<InventoryItem> inventoryItemBeanPropertyRowMapper = new BeanPropertyRowMapper<>(InventoryItem.class);
        inventoryItemBeanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        String query="SELECT * FROM InventoryItem;";
        return jdbcTemplate.query(query, inventoryItemBeanPropertyRowMapper);
    }


}
