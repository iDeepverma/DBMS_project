package com.shop.demo.api;

import com.shop.demo.model.CustomerOrder;
import com.shop.demo.model.CustomerOrderItem;
import com.shop.demo.service.CustomerOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customerOrderItem")
public class CustomerOrderItemApi {

    private CustomerOrderItemService customerOrderItemService;

    @Autowired public CustomerOrderItemApi(CustomerOrderItemService customerOrderItemservice){
        this.customerOrderItemService=customerOrderItemservice;

    }

    @PostMapping
    public int insertCustomerOrderItem(@RequestBody CustomerOrderItem customerOrderItem) {
        return customerOrderItemService.insertCustomerOrderItem(customerOrderItem);
    }

    @DeleteMapping(path = "{id}&{prid}")
    public int deleteCustomerOrderItem(@PathVariable("id") int id,@PathVariable("prid") int productID) {
        return customerOrderItemService.deleteCustomerOrderItem(id,productID);
    }

//    public List<CustomerOrderItem> getCustomerOrderItemByCustomerOrder(CustomerOrder customerOrder) {
//        return customerOrderItemService.getCustomerOrderItemByCustomerOrder(customerOrder);
//    }

//    public int getProfitPerOrderItem(CustomerOrderItem customerOrderItem) {
//        return customerOrderItemService.getProfitPerOrderItem(customerOrderItem);
//    }

    @GetMapping
    public List<CustomerOrderItem> getAllCustomerOrderItem(){
        return customerOrderItemService.getAllCustomerOrderItem();
    }
}
