package com.shop.demo.service;

import com.shop.demo.dao.ProductSupplierDAO;
import com.shop.demo.dao.SupplyOrderItemDAO;
import com.shop.demo.model.SupplyOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class SupplyOrderItemService {
    private SupplyOrderItemDAO supplyOrderItemDAO;
    @Autowired
    public SupplyOrderItemService(@Qualifier("supplyOrderItem_mysql_repo") SupplyOrderItemDAO supplyOrderItemDAO) {this.supplyOrderItemDAO = supplyOrderItemDAO;
    }
    public int insertSupplyOrderItem(SupplyOrderItem supplyOrderItem) {
        return supplyOrderItemDAO.insertSupplyOrderItem(supplyOrderItem);
    }

    public int deleteSupplyOrderItem(int productID, int supplyOrderID) {
        return supplyOrderItemDAO.deleteSupplyOrderItem(productID , supplyOrderID);
    }

    public int updateSupplyOrderItem(int productID, int supplyOrderID, SupplyOrderItem supplyOrderItem) {
        return supplyOrderItemDAO.updateSupplyOrderItem(productID, supplyOrderID, supplyOrderItem);
    }

    public SupplyOrderItem getSupplyOrderItemByID(int productID, int supplyOrderID) {
        return supplyOrderItemDAO.getSupplyOrderItemByID(productID, supplyOrderID);
    }
}
