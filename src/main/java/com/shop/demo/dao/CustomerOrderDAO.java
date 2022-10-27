package com.shop.demo.dao;

import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerOrderDAO {
    int insertCustomerOrder(CustomerOrder customerOrder);
    int deleteCustomerOrder(int id);
    int updateCustomerOrder(int id, CustomerOrder customerOrder);
    int getCustomerOrderByID(int id);
    List<CustomerOrder> getCustomerOrderByCustomer(Customer customer);
    Employee getEmployeeByOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrderBetweenDates(LocalDateTime startingDate, LocalDateTime endingDate);

    int getProfitPerOrder(CustomerOrder customerOrder);

}
