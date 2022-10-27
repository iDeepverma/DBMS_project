package com.shop.demo.dao;

import com.shop.demo.model.InventoryItem;
import com.shop.demo.model.Product;

import java.util.List;

public interface InventoryItemDAO {
    int insertItem(InventoryItem inventoryItem);
    int deleteItem(int id);
    InventoryItem getItemByProduct(Product product);

    int getStock(Product product);
    int markItemSold(int id);
    int updateItem(int id, InventoryItem inventoryItem);
}
