package com.shop.demo.dao;

import com.shop.demo.model.Supplier;

public interface SupplierDAO {
    int insertSupplier(Supplier supplier);
    int deleteSupplier(int id);
    int updateSupplier(int id,Supplier supplier);
    Supplier getSupplierByID(int id);

    int updateMoney(int id, int money);
    int updateOrdersFullfilled(int id, int orders);

}
