package com.shop.demo.dao;

import com.shop.demo.model.SupplyOrder;
import com.shop.demo.model.SupplyOrderItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SupplyOrderDAO {
    int insertSupplyOrder(SupplyOrder supplyOrder);
//    INSERT INTO SupplierOrder VALUES (supplierOrder);

    int deleteSupplyOrder(int id);
//    DELETE FROM SupplierOrder WHERE orderID = id;

    int updateSupplyOrder(int id, SupplyOrder supplyOrder);
//    UPDATE SupplyOrder SET orderDate=?, deliveryDate=?,deliveryStatus=?, totalAmount=?,supplierID=?,placedBy=? WHERE orderID=id;

    SupplyOrder getSupplyOrderByID(int id);
//    select * from SupplyOrder where orderID=id;

    int updateDeliveryStatus(int id, String deliveryStatus);
//    UPDATE SupplyOrder SET deliveryStatus=deliveryStatus WHERE orderID=id;

    int updateDeliveryDate(int id, Date date);
//    UPDATE SupplyOrder SET deliveryDate=date WHERE orderID=id;

    List<SupplyOrderItem> getSupplyItem(SupplyOrder supplyOrder);
    // SELECT * FROM SupplyOrderItem WHERE supplyOrder.orderID= SupplyOrderItem.supplyOrderID;

}
