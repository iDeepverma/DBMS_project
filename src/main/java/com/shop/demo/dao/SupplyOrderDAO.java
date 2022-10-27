package com.shop.demo.dao;

import com.shop.demo.model.SupplyOrder;
import com.shop.demo.model.SupplyOrderItem;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplyOrderDAO {
    int insertSupplyOrder(SupplyOrder supplyOrder);
    int deleteSupplyOrder(int id);
    int updateSupplyOrder(int id, SupplyOrder supplyOrder);
    int getSupplyOrderByID(int id);

    int updateDeliveryStatus(int id, String deliveryStatus);
    int updateDeliveryDate(int id, LocalDateTime date);

    List<SupplyOrderItem> getSupplyItem(SupplyOrder supplyOrder);
}
