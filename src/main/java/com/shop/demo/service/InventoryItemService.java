package com.shop.demo.service;

import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.dao.InventoryItemDAO;
import com.shop.demo.model.InventoryItem;
import com.shop.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryItemService {
    private InventoryItemDAO inventoryItemDAO;
    @Autowired
    public InventoryItemService(@Qualifier("inventoryItem_mysql_repo") InventoryItemDAO inventoryItemDAO) {this.inventoryItemDAO = inventoryItemDAO;
    }
    public int insertItem(InventoryItem inventoryItem) {
        return inventoryItemDAO.insertItem(inventoryItem);
    }
    public int insertItemUnsold(InventoryItem inventoryItem){
        return inventoryItemDAO.insertItemUnsold(inventoryItem);
    }
    public int deleteItem(int itemID, int productID) {
        return inventoryItemDAO.deleteItem(itemID);
    }

    public List<InventoryItem> getItemByProduct(int product) {
        return inventoryItemDAO.getItemByProduct(product);
    }


    public int updateItem(int itemID, int productID, InventoryItem inventoryItem) {
        return inventoryItemDAO.updateItem(itemID , inventoryItem);
    }

    public List<InventoryItem> getAllInventoryItems(){
        return inventoryItemDAO.getAllInventoryItems();
    }
    public int updateOrderID(int itemID, int orderID){
        return inventoryItemDAO.updateOrderID(itemID, orderID);
    }
}
