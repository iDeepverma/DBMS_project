package com.shop.demo.service;

import com.shop.demo.dao.CustomerDAO;
import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.dao.SupplierDAO;
import com.shop.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private SupplierDAO supplierDAO;
    @Autowired
    public  SupplierService(@Qualifier("supplier_mysql_repo") SupplierDAO supplierDAO) {this.supplierDAO = supplierDAO;
    }
    public int insertSupplier(Supplier supplier) {
        return supplierDAO.insertSupplier(supplier);
    }

    public int deleteSupplier(int id) {
        return supplierDAO.deleteSupplier(id);
    }

    public int updateSupplier(int id, Supplier supplier) {
        return supplierDAO.updateSupplier(id,supplier);
    }

    public Supplier getSupplierByID(int id) {
        return supplierDAO.getSupplierByID(id);
    }

    public int updateMoney(int id, int money) {
        return supplierDAO.updateMoney(id,money);
    }

    public int updateOrdersFullfilled(int id, int orders) {
        return supplierDAO.updateOrdersFullfilled(id,orders);
    }

    public List<Supplier> getAllSupplier(){return supplierDAO.getAllSupplier();}
}
