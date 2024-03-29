package com.shop.demo.repository;

import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@Repository("customerOrder_mysql_repo")
public class CustomerOrderMysql implements CustomerOrderDAO {

//    orderID int AUTO_INCREMENT,
//    orderDate date,
//    transactionID int,
//    modeOfPayment varchar(255),
//    total int,
//    customerID int,
//    servedBy int,
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertCustomerOrder(CustomerOrder customerOrder) {
        String query = "INSERT INTO CustomerOrder(orderID , orderDate, transactionID, modeOfPayment,total,customerID, servedBy) VALUES (?,?,?,?,?,?,?);";
        Object[] args = new Object[] {
            customerOrder.getOrderID(),
            customerOrder.getOrderDate(),
            customerOrder.getTransactionID(),
            customerOrder.getModeOfPayment(),
            customerOrder.getTotal(),
            customerOrder.getCustomerID(),
            customerOrder.getServedBy()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteCustomerOrder(int id) {
        String query = "DELETE FROM CustomerOrder WHERE orderID=?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int updateCustomerOrder(int id, CustomerOrder customerOrder) {
        String query = "UPDATE CustomerOrder SET orderDate=?, transactionID=?, modeOfPayment=?, total=?, customerID=?, servedBy=? WHERE orderID=?;";
        Object[] args = new Object[]{
                customerOrder.getOrderDate(),
                customerOrder.getTransactionID(),
                customerOrder.getModeOfPayment(),
                customerOrder.getTotal(),
                customerOrder.getCustomerID(),
                customerOrder.getServedBy(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public CustomerOrder getCustomerOrderByID(int id) {
        //needs to be ressolved
        String query = "SELECT * FROM CustomerOrder WHERE orderID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args,BeanPropertyRowMapper.newInstance(CustomerOrder.class));
    }

    @Override
    public List<CustomerOrder> getCustomerOrderByCustomer(int id) {
        String query = "SELECT * FROM CustomerOrder WHERE CustomerOrder.customerID=?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.query(query,args,BeanPropertyRowMapper.newInstance(CustomerOrder.class));
    }

    @Override
    public Employee getEmployeeByOrder(CustomerOrder customerOrder) {
        String query ="SELECT * FROM Employee WHERE Employee.empID = ?;";
        Object[] args =new Object[]{
          customerOrder.getServedBy()
        };
        return jdbcTemplate.queryForObject(query,args,BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public List<CustomerOrder> getCustomerOrderBetweenDates(Date startingDate,Date endingDate) {
        String query="SELECT * FROM CustomerOrder WHERE orderDate BETWEEN ? AND ?;";
        Object[] args=new Object[]{
                startingDate, endingDate
        };
        return jdbcTemplate.query(query,args,BeanPropertyRowMapper.newInstance(CustomerOrder.class));
    }

    @Override
    public int getProfitPerOrder(CustomerOrder customerOrder) {
        String query="SELECT SUM((quantity*(CustomerOrderItem.sellingPrice-Product.costPrice))) as profit FROM Product,CustomerOrderItem WHERE CustomerOrderItem.orderID=? AND Product.productID = CustomerOrderItem.productID;";
        Object[] args=new Object[]{
          customerOrder.getOrderID()
        };
           return jdbcTemplate.queryForObject(query,args,BeanPropertyRowMapper.newInstance(Integer.class));
    }

    @Override
    public List<CustomerOrder> getAllCustomerOrders(){
        String query="SELECT * FROM CustomerOrder ORDER BY orderDate DESC ";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(CustomerOrder.class));
    }

    @Override
    public int getTotalSales(){
        String query="SELECT sum(total) FROM CustomerOrder";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
