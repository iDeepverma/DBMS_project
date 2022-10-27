package com.shop.demo.dao;

import com.shop.demo.model.Supplier;
import com.shop.demo.model.SupplyOrderItem;

import java.util.List;

public interface SupplyOrderItemDAO {
    int insertSupplyOrderItem(SupplyOrderItem supplyOrderItem);
    int deleteSupplyOrderItem(int productID, int supplyOrderID);

    int updateSupplyOrderItem(int productID, int supplyOrderID, SupplyOrderItem supplyOrderItem);
    SupplyOrderItem getSupplyOrderItemByID(int productID, int supplyOrderID);
}
