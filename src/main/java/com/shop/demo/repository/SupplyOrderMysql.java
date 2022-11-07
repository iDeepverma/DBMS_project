package com.shop.demo.repository;

import com.shop.demo.dao.SupplyOrderDAO;
import com.shop.demo.model.Product;
import com.shop.demo.model.SupplyOrder;
import com.shop.demo.model.SupplyOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Repository("supplyOrder_mysql_repo")
public class SupplyOrderMysql implements SupplyOrderDAO {

    BeanPropertyRowMapper<SupplyOrder> supplyOrderBeanPropertyRowMapper = new BeanPropertyRowMapper<>(SupplyOrder.class);
    BeanPropertyRowMapper<SupplyOrderItem> supplyOrderItemBeanPropertyRowMapper = new BeanPropertyRowMapper<>(SupplyOrderItem.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int insertSupplyOrder(SupplyOrder supplyOrder) {
        String query="INSERT INTO SupplyOrder(orderDate,deliveryDate,deliveryStatus,totalAmount,supplierID,placedBy) VALUES (?,?,?,?,?,?);";
        Object[] args=new Object[]{
                supplyOrder.getOrderDate(),
                supplyOrder.getDeliveryDate(),
                supplyOrder.getDeliveryStatus(),
                supplyOrder.getTotalAmount(),
                supplyOrder.getSupplierID(),
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
                supplyOrder.getSupplierID(),
                supplyOrder.getPlacedBy(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public SupplyOrder getSupplyOrderByID(int id) {

        supplyOrderBeanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        String query="select * from SupplyOrder where orderID=?;";
        Object[] args=new Object[]{
          id
        };
        return jdbcTemplate.queryForObject(query,args, supplyOrderBeanPropertyRowMapper);

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
    public int updateDeliveryDate(int id, Date date) {
        String query="UPDATE SupplyOrder SET deliveryDate=? WHERE orderID=?;";
        Object[] args=new Object[]{
                date,
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<SupplyOrderItem> getSupplyItem(SupplyOrder supplyOrder) {

        supplyOrderItemBeanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        String query="SELECT * FROM SupplyOrderItem WHERE SupplyOrderItem.supplyOrderID=?;";
        Object[] args =new Object[]{
                supplyOrder.getOrderID()
        };
        return jdbcTemplate.query(query,args, supplyOrderItemBeanPropertyRowMapper);
    }

    @Override
    public List<SupplyOrder> getAllSupplyOrders(){

        supplyOrderBeanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        String query="SELECT * FROM SupplyOrder ORDER BY orderDate DESC";
        return jdbcTemplate.query(query , supplyOrderBeanPropertyRowMapper);
    }
}
