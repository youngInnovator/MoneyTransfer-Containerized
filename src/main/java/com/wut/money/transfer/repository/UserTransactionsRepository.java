package com.wut.money.transfer.repository;

import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.UserTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTransactionsRepository extends JpaRepository<UserTransactions, Integer> {

    public List<UserTransactions> findAllByAccountNumber(Account account);
}
