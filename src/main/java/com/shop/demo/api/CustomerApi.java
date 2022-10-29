package com.shop.demo.api;

import com.shop.demo.model.Customer;
import com.shop.demo.model.Employee;
import com.shop.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerApi {

    private CustomerService customerService;

    @Autowired
    public CustomerApi(CustomerService customerService){
        this.customerService=customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @DeleteMapping(path = "{id}")
    public int deleteCustomer(@PathVariable("id") int id){
        return customerService.deleteCustomer(id);
    }

    @PostMapping
    public int insertCustomer(@RequestBody Customer customer){
        return customerService.insertCustomer(customer);
    }

    @PutMapping(path = "{id}")
    public int updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @GetMapping("{id}")
    public Customer getCustomerByID(@PathVariable("id") int id){
        return customerService.getCustomerByID(id);
    }

    @GetMapping(path="phone/{phone}")
    public Customer getCustomerByNumber(@PathVariable("phone") String phone){
        return customerService.getCustomerByNumber(phone);
    }
    @GetMapping(path = "mail/{mail}")
    public Customer getCustomerByEmail(@PathVariable("mail") String mail){
        return customerService.getCustomerByEmail(mail);
    }


}
