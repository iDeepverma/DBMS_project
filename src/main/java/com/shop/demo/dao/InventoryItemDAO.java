package com.shop.demo.dao;

import com.shop.demo.model.InventoryItem;

public interface InventoryItemDAO {
    int insertItem(InventoryItem inventoryItem);
    int deleteItem(int id);
    InventoryItem getItemByID(int id);
    int updateItem(int id, InventoryItem inventoryItem);
}
