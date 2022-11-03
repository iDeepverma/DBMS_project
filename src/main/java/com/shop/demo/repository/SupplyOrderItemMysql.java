package com.shop.demo.repository;

import com.shop.demo.dao.SupplyOrderItemDAO;
import com.shop.demo.model.Employee;
import com.shop.demo.model.SupplyOrder;
import com.shop.demo.model.SupplyOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//          supplyOrderID int,
//        quantity int,
//        total int,
//        productID int,
//        additionalInfo LONGTEXT,

@Repository("supplyOrderItem_mysql_repo")
public class SupplyOrderItemMysql implements SupplyOrderItemDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public BeanPropertyRowMapper<SupplyOrderItem> supplyOrderItemBeanPropertyRowMapper = new BeanPropertyRowMapper<>(SupplyOrderItem.class);

    @Override
    public int insertSupplyOrderItem(SupplyOrderItem supplyOrderItem) {
        String query = "INSERT INTO SupplyOrderItem(supplyOrderID,quantity,total,productID,additionalInfo) VALUES (?,?,?,?,?);";
        Object[] args = new Object[] {
                supplyOrderItem.getSupplyOrderID(),
                supplyOrderItem.getQuantity(),
                supplyOrderItem.getTotal(),
                supplyOrderItem.getProductID(),
                supplyOrderItem.getAdditionalInfo()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteSupplyOrderItem(int productID, int supplyOrderID) {
        String query = "DELETE FROM SupplyOrderItem WHERE SupplyOrderItem.productID=? AND SupplyOrderItem.supplyOrderID=?;";
        Object[] args = new Object[] {
                productID,
                supplyOrderID
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateSupplyOrderItem(int productID, int supplyOrderID, SupplyOrderItem supplyOrderItem) {
        String query = "UPDATE SupplyOrderItem SET quanitity=?,total=?,additionalInfo=? WHERE SupplyOrderItem.productID = ? AND SupplyOrderItem.supplyOrderID=?;";
        Object[] args = new Object[] {
                supplyOrderItem.getQuantity(),
                supplyOrderItem.getTotal(),
                supplyOrderItem.getAdditionalInfo(),
                productID,
                supplyOrderID
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public SupplyOrderItem getSupplyOrderItemByID(int productID, int supplyOrderID) {
        supplyOrderItemBeanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);
        String query = "SELECT * FROM SupplyOrderItem WHERE SupplyOrderItem.productId=? AND SupplyOrderItem.supplierID=?;";
        Object[] args = new Object[] {
                productID,
                supplyOrderID
        };

        return jdbcTemplate.queryForObject(query,args, supplyOrderItemBeanPropertyRowMapper);
    }
}
