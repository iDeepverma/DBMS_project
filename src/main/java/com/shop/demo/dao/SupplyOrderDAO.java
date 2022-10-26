package com.shop.demo.dao;

import com.shop.demo.model.SupplyOrder;

public interface SupplyOrderDAO {
    int insertSupplyOrder(SupplyOrder supplyOrder);
    int deleteSupplyOrder(int id);
    int updateSupplyOrder(int id, SupplyOrder supplyOrder);
    int getSupplyOrderByID(int id);
}
