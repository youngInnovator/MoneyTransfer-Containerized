package com.wut.money.transfer.controller;


import com.wut.money.transfer.entity.Customer;
import com.wut.money.transfer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        Customer savedCustomer = null;
        try {
            savedCustomer = this.customerService.createNewCustomer(customer);
            return ResponseEntity.of(Optional.of(savedCustomer));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("customerId") int  customerId){
        Customer customer = null;
        try{
            customer = this.customerService.findCustomerById(customerId);
            return ResponseEntity.of(Optional.of(customer));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable("customerId") int customerId){

        try {
           Customer customer1 = this.customerService.updateCustomer(customer,customerId);
           return ResponseEntity.of(Optional.of(customer1));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable int customerId){

        Customer customer = null;
        try {
            customer = this.customerService.deleteCustomerById(customerId);
            return ResponseEntity.of(Optional.of(customer));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> finAllCustomer( ){
        List<Customer> customerList = this.customerService.finAllCustomer();
        try {
            return ResponseEntity.of(Optional.of(customerList));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getCustomerByName/{customerName}")
    public ResponseEntity<List<Customer>> finCustomerByLastName(@PathVariable String customerName){
        List<Customer> customers = this.customerService.finCustomerByLastName(customerName);
        try {
            return ResponseEntity.of(Optional.of(customers));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/getCustomer/{customerId}/{userPin}")
    public ResponseEntity<Customer> userLogin(@PathVariable("customerId") int customerId,@PathVariable("userPin") String userPin){

        Customer customer = this.customerService.userLogin(customerId, userPin);

        try {
            return ResponseEntity.of(Optional.of(customer));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
