package com.shop.demo.dao;

import com.shop.demo.model.Supplier;

import java.util.List;

public interface SupplierDAO {
    int insertSupplier(Supplier supplier);
//    INSERT INTO Supplier VALUES (supplier);

    int deleteSupplier(int id);
//    DELETE FROM Supplier WHERE supplierID = id;

    int updateSupplier(int id,Supplier supplier);
//    UPDATE Supplier SET name=?, phone=?,address=?, moneySpent=?,ordersFulfilled=?,email=? WHERE supplierID=id;

    Supplier getSupplierByID(int id);
//    select * from Supplier where supplierID=id;

    int updateMoney(int id, int money);
//    UPDATE Supplier SET moneySpent=money WHERE supplierID=id;

    int updateOrdersFullfilled(int id, int orders);
//    UPDATE Supplier SET ordersFulfilled=orders WHERE supplierID=id;

    List<Supplier> getAllSupplier();

}
