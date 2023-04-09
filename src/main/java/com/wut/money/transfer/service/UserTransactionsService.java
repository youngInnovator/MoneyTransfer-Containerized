package com.wut.money.transfer.service;

import com.wut.money.transfer.dto.TransferRequestDTO;
import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.UserTransactions;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserTransactionsService {

    public UserTransactions saveTransaction(UserTransactions userTransactions);
    public UserTransactions getTransactionById(int transactionId);
    public List<UserTransactions> getAllTransactionByAccount(int accountId);

    public List<UserTransactions> getAllTransaction();
    public List<UserTransactions> getAccountTransactionHistory(int accountId);

    public void transferAmountToOtherAccount(TransferRequestDTO transferRequest);

    public List<UserTransactions> getAllDebitedAmount(int accountId);

    public List<UserTransactions> getAllCreditedAmount(int accountId);




}
