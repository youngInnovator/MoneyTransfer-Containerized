package com.wut.money.transfer.service;

import com.wut.money.transfer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public Customer createNewCustomer(Customer customer);
    public Customer findCustomerById(int  customerId);
    public Customer updateCustomer(Customer customer, int customerId);
    public Customer deleteCustomerById(int customerId);
    public List<Customer> finAllCustomer( );
    public List<Customer> finCustomerByLastName(String customerName);

    public Customer userLogin(int customerId, String userPin);
}
