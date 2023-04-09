package com.wut.money.transfer.service.implementation;

import com.wut.money.transfer.dto.TransferRequestDTO;
import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.UserTransactions;
import com.wut.money.transfer.exceptions.AccountNotFoundException;
import com.wut.money.transfer.exceptions.BalanceLessThanWithdrawAmountException;
import com.wut.money.transfer.repository.AccountRepository;
import com.wut.money.transfer.repository.UserTransactionsRepository;
import com.wut.money.transfer.service.AccountService;
import com.wut.money.transfer.service.UserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTransactionsServiceImpl implements UserTransactionsService {


    private final UserTransactionsRepository transactionsRepository;

    private final AccountService accountService;

    private final AccountRepository accountRepository;



    public UserTransactionsServiceImpl(UserTransactionsRepository transactionsRepository, AccountService accountService, AccountRepository accountRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Override
    public UserTransactions saveTransaction(UserTransactions userTransactions) {
        return this.transactionsRepository.save(userTransactions);
    }

    @Override
    public UserTransactions getTransactionById(int transactionId) {
        Optional<UserTransactions> optionalUserTransactions = this.transactionsRepository.findById(transactionId);
        return optionalUserTransactions.orElse(null);
    }

    @Override
    public List<UserTransactions> getAllTransactionByAccount(int accountId) {

       Account account1 = this.accountService.getAccountById(accountId);
        return this.transactionsRepository.findAllByUserAccount(account1);
    }

    @Override
    public List<UserTransactions> getAccountTransactionHistory(int accountId) {
        Account account = this.accountService.getAccountById(accountId);
        return this.transactionsRepository.findAllByUserAccount(account);
    }


    @Override
    public void transferAmountToOtherAccount(TransferRequestDTO transferRequest){
        Account transferAccount = this.accountRepository.findById(transferRequest.getAccountIdFrom())
                .orElseThrow(() ->new AccountNotFoundException("Sender account not found"));
        Account receiverAccount = this.accountRepository.findById(transferRequest.getAccountIdTO())
                .orElseThrow(() ->new AccountNotFoundException("Receiver account not found"));

        double amountToTransfer = transferRequest.getBalance();
        double transferAccountBalance = transferAccount.getAccountBalance();

        if(amountToTransfer > transferAccountBalance){
            throw new BalanceLessThanWithdrawAmountException();
        }else {

            Account accountFrom = this.accountService.withdrawalAccount(amountToTransfer, transferAccount.getAccountId());
            UserTransactions userTransactions = new UserTransactions();
            userTransactions.setTransactionType("Debit");
            userTransactions.setUserAccount(accountFrom);
            userTransactions.setCurrencyType(accountFrom.getAccountCurrency());
            userTransactions.setAmount(amountToTransfer);
            userTransactions.setDateTime(LocalDateTime.now());
            userTransactions.setAccountBalance(transferAccount.getAccountBalance() - amountToTransfer);

            UserTransactions userTransactions1 = this.transactionsRepository.save(userTransactions);

            System.out.println(userTransactions1.getAmount());


            Account accountTo = this.accountService.depositMoney(amountToTransfer, receiverAccount.getAccountId());
            UserTransactions userTransactionsCredit = new UserTransactions();
            userTransactionsCredit.setTransactionType("Credit");
            userTransactionsCredit.setUserAccount(accountTo);
            userTransactionsCredit.setCurrencyType(accountTo.getAccountCurrency());
            userTransactionsCredit.setAmount(amountToTransfer);
            userTransactionsCredit.setAccountBalance(receiverAccount.getAccountBalance() + amountToTransfer);
            userTransactionsCredit.setDateTime(LocalDateTime.now());

            UserTransactions userTransactions2 = this.transactionsRepository.save(userTransactionsCredit);

            System.out.println(userTransactions2.getAmount());

        }

    }

    @Override
    public List<UserTransactions> getAllTransaction(){
        return this.transactionsRepository.findAll();
    }

    @Override
    public List<UserTransactions> getAllDebitedAmount(int accountId){
        Account account = this.accountService.getAccountById(accountId);
        List<UserTransactions> transactions = this.transactionsRepository.findAllByUserAccount(account);
        List<UserTransactions> debitTransactions = new ArrayList<>();
//        debitTransactions = transactions.stream().map(transaction -> transaction.getTransactionType() == "Credit").collect(Collectors.toList());
        for (UserTransactions transaction:transactions) {
            if(transaction.getTransactionType().equals("Debit")){
                debitTransactions.add(transaction);
            }
        }
        return debitTransactions;
    }
    @Override
    public List<UserTransactions> getAllCreditedAmount(int accountId){

        Account account = this.accountService.getAccountById(accountId);
        List<UserTransactions> transactions = this.transactionsRepository.findAllByUserAccount(account);
        List<UserTransactions> creditedTransactions = new ArrayList<>();
//        debitTransactions = transactions.stream().map(transaction -> transaction.getTransactionType() == "Credit").collect(Collectors.toList());
        for (UserTransactions transaction:transactions) {
            if(transaction.getTransactionType().equals("Credit")){
                creditedTransactions.add(transaction);
            }
        }
        return creditedTransactions;
    }


}
