package com.wut.money.transfer.service.implementation;


import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.Customer;
import com.wut.money.transfer.repository.CustomerRepository;
import com.wut.money.transfer.service.CustomerService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(int customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if(customer.isPresent()){
            return customer.get();
        }else {
            return null;
        }

    }

    @Override
    public Customer updateCustomer(Customer customer, int customerId) {

        Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            Customer updateCustomer = optionalCustomer.get();

            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setPassword(customer.getPassword());
            updateCustomer.setPin(customer.getPin());

            List<Account> accounts = customer.getAccount();
            if (accounts.isEmpty()){
                updateCustomer.setAccount(updateCustomer.getAccount());
            }else {
                updateCustomer.setAccount(accounts);
            }
            this.customerRepository.save(updateCustomer);
            return updateCustomer;
        }else {
            return null;
        }
    }

    @Override
    public Customer deleteCustomerById(int customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if(customer.isPresent()){
            this.customerRepository.deleteById(customerId);
            return customer.get();
        }else {
            return null;
        }
    }

    @Override
    public List<Customer> finAllCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<Customer> finCustomerByLastName(String lastName) {
        return this.customerRepository.findAllByLastName(lastName);
    }

    public Customer userLogin(int customerId, String userPin){
        Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);

        Customer customer = this.findCustomerById(customerId);

//
//        System.out.println(Integer.parseInt(customer.getPin()) == Integer.parseInt(userPin));
//        System.out.println(customer.getPin().toString());
//        System.out.println(userPin.toString());

//        return customer;
        if (Integer.parseInt(customer.getPin()) == Integer.parseInt(userPin)){
            return customer;
        }else {
            return null;
        }

//        if(optionalCustomer.isPresent()){
//             // optionalCustomer.stream().map(emp->emp.getPassword() ==userPassword).collect(Collectors.toList());
//            System.out.println(optionalCustomer.get().getPassword().toString());
//            System.out.println(userPassword.toString());
//            if(optionalCustomer.get().getPassword().toString() == userPassword.toString())
//                return optionalCustomer.get();
//        }
//            return null;
    }


}
