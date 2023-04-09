package com.wut.money.transfer.repository;

import com.wut.money.transfer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public List<Customer> findAllByLastName(String lastName);

}
