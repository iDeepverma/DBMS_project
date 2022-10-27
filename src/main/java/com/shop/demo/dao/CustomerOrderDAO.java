package com.shop.demo.dao;

import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;

import javax.validation.constraints.AssertFalse;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomerOrderDAO {
    int insertCustomerOrder(CustomerOrder customerOrder);
    //INSERT INTO CustomerOrder VALUES (customerOrder);
    int deleteCustomerOrder(int id);
    //DELETE FROM CustomerOrder WHERE orderID=id;
    int updateCustomerOrder(int id, CustomerOrder customerOrder);
    //UPDATE CustomerOrder SET orderDate=?, transactionID=?, modeOfPayment=?, total=?, customerID=?, servedBy=? WHERE orderID=id;
    int getCustomerOrderByID(int id);
    //anomaly
    //SELECT * FROM CustomerOrder WHERE orderID = id;
    List<CustomerOrder> getCustomerOrderByCustomer(Customer customer);
    //SELECT * FROM CustomerOrder WHERE CustomerOrder.customerID=customer.customerID;
    Employee getEmployeeByOrder(CustomerOrder customerOrder);
    //SELECT * FROM Employee WHERE Employee.empID = customerOrder.servedBy;

    List<CustomerOrder> getCustomerOrderBetweenDates(LocalDateTime startingDate, LocalDateTime endingDate);
    //SELECT * FROM CustomerOrder WHERE orderDate BETWEEN startingDate AND endingDate;
    int getProfitPerOrder(CustomerOrder customerOrder);
    //SELECT profit as SUM(quantity*(CustomerOrderItem.sellingPrice-Product.costPrice)) FROM Product,CustomerOrderItem WHERE CustomerOrderItem.orderID=customerOrder.orderID AND Product.productID = CustomerOrderItem.productID;
}
