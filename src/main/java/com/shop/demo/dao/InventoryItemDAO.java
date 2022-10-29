package com.shop.demo.dao;

import com.shop.demo.model.InventoryItem;
import com.shop.demo.model.Product;

import java.util.List;

public interface InventoryItemDAO {
    int insertItem(InventoryItem inventoryItem);
//    INSERT INTO InventoryItem() VALUES (inventoryItem);

    int deleteItem(int itemID,int productID);
//    DELETE FROM InventoryItem WHERE itemID=itemID AND productID=productID;

//change kia h phele (product product ) tha
    InventoryItem getItemByProduct(int product);
//    select * from InventoryItem where productID=product;


    int getStock(Product product);
    //SELECT amountInStock FROM Product WHERE Product.productID=?;
    int markItemSold(int itemID,int product);
//    UPDATE PRODUCT SET amountInStock = amountInStock-1 WHERE InventoryItem.itemID = itemID AND InventoryItem.productID = product;


    int updateItem(int itemID,int productID, InventoryItem inventoryItem);
//    UPDATE InventoryItem SET supplyOrderID=?, orderID=?  WHERE InventoryItem.itemID=itemID AND InventoryItem.productID=productID;


}
