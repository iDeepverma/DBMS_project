package com.shop.demo.dao;

import com.shop.demo.model.Supplier;
import com.shop.demo.model.SupplyOrderItem;

import java.util.List;

public interface SupplyOrderItemDAO {
    int insertSupplyOrderItem(SupplyOrderItem supplyOrderItem);
    //INSERT INTO SupplyOrderItem VALUES supplyOrderItem;
    int deleteSupplyOrderItem(int productID, int supplyOrderID);
    //DELETE FROM SupplyOrderItem WHERE SupplyOrderItem.productID = productID AND SupplyOrderItem.supplyOrderID = supplyOrderID;
    int updateSupplyOrderItem(int productID, int supplyOrderID, SupplyOrderItem supplyOrderItem);
    //UPDATE SupplyOrderItem SET SupplyOrderItem=supplyOrderItem WHERE SupplyOrderItem.productID = productID AND SupplyOrderItem.supplyOrderID=supplyOrderItem;
    SupplyOrderItem getSupplyOrderItemByID(int productID, int supplyOrderID);
    //SELECT * FROM SupplyOrderItem WHERE SupplyOrderItem.productId = ProductID AND SupplyOrderItem.supplierID=?;
}
