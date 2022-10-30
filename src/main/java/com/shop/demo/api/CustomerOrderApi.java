package com.shop.demo.api;

import com.shop.demo.model.CustomerOrder;
import com.shop.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class CustomerOrderApi<customer> {

    private CustomerOrderService customerOrderService;

    @Autowired public  CustomerOrderApi(CustomerOrderService customerOrderService)
    { this.customerOrderService=customerOrderService;}

    @PostMapping
    public int insertCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        return customerOrderService.insertCustomerOrder(customerOrder);
    }

    @DeleteMapping(path = "{id}")
    public int deleteCustomerOrder(@PathVariable("id") int id) {
        return customerOrderService.deleteCustomerOrder(id);
    }

    @PutMapping(path = "{id}")
    public int updateCustomerOrder(@PathVariable("id") int id,@RequestBody CustomerOrder customerOrder) {
        return customerOrderService.updateCustomerOrder(id,customerOrder);
    }

    @GetMapping("{id}")
    public CustomerOrder getCustomerOrderByID(@PathVariable("id") int id) {
        return customerOrderService.getCustomerOrderByID(id);
    }

//    @GetMapping
//    public List<CustomerOrder> getCustomerOrderByCustomer(RequestBody Customer customer){
//
//        return customerOrderService.getCustomerOrderByCustomer(customer);
//    }

//    @GetMapping
//    public Employee getEmployeeByOrder(@RequestBody CustomerOrder customerOrder) {
//        return customerOrderService.getEmployeeByOrder(customerOrder);
//    }




}
