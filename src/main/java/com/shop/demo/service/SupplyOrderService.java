package com.shop.demo.service;

import com.shop.demo.dao.SupplyOrderDAO;
import com.shop.demo.model.SupplyOrder;
import com.shop.demo.model.SupplyOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class SupplyOrderService {

    private SupplyOrderDAO supplyOrderDAO;

    @Autowired
    SupplyOrderService(@Qualifier("supplyOrder_mysql_repo") SupplyOrderDAO supplyOrderDAO) {
        this.supplyOrderDAO = supplyOrderDAO;
    }

    public int insertSupplyOrder(SupplyOrder supplyOrder) {

        return supplyOrderDAO.insertSupplyOrder(supplyOrder);
    }

    public int deleteSupplyOrder(int id) {

        return supplyOrderDAO.deleteSupplyOrder(id);
    }


    public int updateSupplyOrder(int id, SupplyOrder supplyOrder) {
        return supplyOrderDAO.updateSupplyOrder(id, supplyOrder);
    }

    public SupplyOrder getSupplyOrderByID(int id) {
        return supplyOrderDAO.getSupplyOrderByID(id);
    }

    public int updateDeliveryStatus(int id, String deliveryStatus) {
        return supplyOrderDAO.updateDeliveryStatus(id, deliveryStatus);
    }

    public int updateDeliveryDate(int id, Date date) {
        return supplyOrderDAO.updateDeliveryDate(id, date);
    }

    public List<SupplyOrderItem> getSupplyItem(SupplyOrder supplyOrder) {
        return supplyOrderDAO.getSupplyItem(supplyOrder);
    }
}
