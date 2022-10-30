package com.shop.demo.service;

import com.shop.demo.dao.CustomerOrderDAO;
import com.shop.demo.dao.CustomerOrderItemDAO;
import com.shop.demo.dao.EmployeeDAO;
import com.shop.demo.model.Customer;
import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerOrderService {
    private CustomerOrderDAO customerOrderDAO;
    @Autowired
    public  CustomerOrderService(@Qualifier("customerOrder_mysql_repo") CustomerOrderDAO customerOrderDAO) {this.customerOrderDAO = customerOrderDAO;
    }
    public int insertCustomerOrder(CustomerOrder customerOrder) {
        return customerOrderDAO.insertCustomerOrder(customerOrder);
    }

    public int deleteCustomerOrder(int id) {
        return customerOrderDAO.deleteCustomerOrder(id);
    }

    public int updateCustomerOrder(int id, CustomerOrder customerOrder) {
        return customerOrderDAO.updateCustomerOrder(id,customerOrder);
    }

    public CustomerOrder getCustomerOrderByID(int id) {
        return customerOrderDAO.getCustomerOrderByID(id);
    }

    public List<CustomerOrder> getCustomerOrderByCustomer(Customer customer) {
        return customerOrderDAO.getCustomerOrderByCustomer(customer);
    }

    public Employee getEmployeeByOrder(CustomerOrder customerOrder) {
        return customerOrderDAO.getEmployeeByOrder(customerOrder);
    }

    public List<CustomerOrder> getCustomerOrderBetweenDates(LocalDate startingDate, LocalDate endingDate) {
        return customerOrderDAO.getCustomerOrderBetweenDates(startingDate,endingDate);
    }
    public int getProfitPerOrder(CustomerOrder customerOrder) {
        return customerOrderDAO.getProfitPerOrder(customerOrder);
    }

    public List<CustomerOrder> getAllCustomerOrder() {
        return  customerOrderDAO.getAllCustomerOrder();
    }
}
