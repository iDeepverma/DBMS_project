package com.shop.demo.dao;

import com.shop.demo.model.InventoryItem;
import com.shop.demo.model.Product;

import java.util.List;

public interface InventoryItemDAO {
    int insertItem(InventoryItem inventoryItem);
//    INSERT INTO InventoryItem() VALUES (inventoryItem);

    int deleteItem(int itemID);
//    DELETE FROM InventoryItem WHERE itemID=itemID AND productID=productID;

//change kia h phele (product product ) tha
    List<InventoryItem> getItemByProduct(int product);
//    select * from InventoryItem where productID=product;


    int updateItem(int itemID,InventoryItem inventoryItem);
//    UPDATE InventoryItem SET supplyOrderID=?, orderID=?  WHERE InventoryItem.itemID=itemID AND InventoryItem.productID=productID;

    List<InventoryItem> getAllInventoryItems();

}
