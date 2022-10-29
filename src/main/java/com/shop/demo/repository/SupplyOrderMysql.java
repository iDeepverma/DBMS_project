package com.shop.demo.repository;

import com.shop.demo.dao.SupplyOrderDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.SupplyOrder;
import com.shop.demo.model.SupplyOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository("mysql_repo")

public class SupplyOrderMysql implements SupplyOrderDAO {

//    orderID int AUTO_INCREMENT,
//    orderDate date,
//    deliveryDate date,
//    deliveryStatus varchar(255),
//    totalAmount int,
//    supplierID int,
//    placedBy int,

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertSupplyOrder(SupplyOrder supplyOrder) {
        String query="INSERT INTO SupplierOrder(orderID,orderDate,deliveryDate,deliveryStatus,totalAmount,supplierID,placedBy) VALUES (?,?,?,?,?,?,?);";
        Object[] args=new Object[]{
                supplyOrder.getOrderID(),
                supplyOrder.getOrderDate(),
                supplyOrder.getDeliveryDate(),
                supplyOrder.getDeliveryStatus(),
                supplyOrder.getTotalAmount(),
                supplyOrder.getSupplier(),
                supplyOrder.getPlacedBy()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteSupplyOrder(int id) {
        String query="DELETE FROM SupplierOrder WHERE orderID = ?;";
        Object[] args=new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateSupplyOrder(int id, SupplyOrder supplyOrder) {
        String query="UPDATE SupplyOrder SET orderDate=?, deliveryDate=?,deliveryStatus=?, totalAmount=?,supplierID=?,placedBy=? WHERE orderID=?;";
        Object[] args=new Object[]{
                supplyOrder.getOrderDate(),
                supplyOrder.getDeliveryDate(),
                supplyOrder.getDeliveryStatus(),
                supplyOrder.getTotalAmount(),
                supplyOrder.getSupplier(),
                supplyOrder.getPlacedBy(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<SupplyOrder> getSupplyOrderByID(int id) {
        String query="select * from SupplyOrder where orderID=?;";
        Object[] args=new Object[]{
          id
        };
        return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(SupplyOrder.class));

    }

    @Override
    public int updateDeliveryStatus(int id, String deliveryStatus) {
        String query="UPDATE SupplyOrder SET deliveryStatus=? WHERE orderID=?;";
        Object[] args=new Object[]{
                deliveryStatus,
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateDeliveryDate(int id, LocalDateTime date) {
        String query="UPDATE SupplyOrder SET deliveryDate=? WHERE orderID=?;";
        Object[] args=new Object[]{
                date,
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<SupplyOrderItem> getSupplyItem(SupplyOrder supplyOrder) {
        String query="SELECT * FROM SupplyOrderItem WHERE SupplyOrderItem.supplyOrderID=?;";
        Object[] args =new Object[]{
                supplyOrder.getOrderID()
        };
        return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(SupplyOrderItem.class));
    }
}
