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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserTransactionsServiceImpl implements UserTransactionsService {

    final
    UserTransactionsRepository transactionsRepository;

    final
    AccountService accountService;

    final
    AccountRepository accountRepository;

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
        if(optionalUserTransactions.isPresent()){
            return optionalUserTransactions.get();
        }else {
            return null ;
        }
    }

    @Override
    public List<UserTransactions> getAllTransactionByAccount(int accountId) {

       Account account1 = this.accountService.getAccountById(accountId);

        return this.transactionsRepository.findAllByAccountNumber(account1);
    }

    @Override
    public List<UserTransactions> getAccountTransactionHistory(int accountId) {
        Account account1 = this.accountService.getAccountById(accountId);

        return this.transactionsRepository.findAllByAccountNumber(account1);
    }


    @Override
    public void transferAmountToOtherAccount(TransferRequestDTO transferRequest){
        Account transferAccount = this.accountRepository.findById(transferRequest.getAccountIdFrom())
                .orElseThrow(() ->new AccountNotFoundException("Sender account not found"));
        Account receiverAccount = this.accountRepository.findById(transferRequest.getAccountIdTO())
                .orElseThrow(() ->new AccountNotFoundException("Receiver account not found"));

        double amountToTransfer = transferRequest.getBalance();
        double transferAccountAmount = transferAccount.getAccountBalance();

        if(amountToTransfer > transferAccountAmount){
            throw new BalanceLessThanWithdrawAmountException();
        }else {
             Account accountFrom = this.accountService.withdrawalAccount(amountToTransfer, transferAccount.getAccountId());
             Account accountTo = this.accountService.depositMoney(amountToTransfer, receiverAccount.getAccountId());

             UserTransactions userTransactions = new UserTransactions();
            userTransactions.setAccountNumber(transferAccount);
            userTransactions.setBalance(amountToTransfer);
            userTransactions.setDateTime(LocalDateTime.now());

        }

    }

    @Override
    public List<UserTransactions> getAllTransaction(){
        return this.transactionsRepository.findAll();
    }


    @Override
    public void transferMoneyToOtherAccount(TransferRequestDTO transferRequest) {
//        Account transferAccount = this.accountRepository.findById(transferRequest.getAccountIdFrom())
//                .orElseThrow(() ->new AccountNotFoundException("Sender account not found"));
//        Account receiverAccount = this.accountRepository.findById(transferRequest.getAccountIdTO())
//                .orElseThrow(() ->new AccountNotFoundException("Receiver account not found"));
//
//        double amountToTransfer = transferRequest.getBalance();
//        double transferAccountAmount = transferAccount.getAccountBalance();
//
//        if(amountToTransfer > transferAccountAmount){
//            throw new BalanceLessThanWithdrawAmountException();
//        }else {
//            Account accountFrom = this.accountService.withdrawalAccount(amountToTransfer, transferAccount.getAccountId());
//            this.accountService.updateBankAccount(accountFrom);
//
//            Account accountTo = this.accountService.depositMoney(amountToTransfer, receiverAccount.getAccountId());
//            this.accountService.updateBankAccount(accountTo);
//        }
//        transferAccount.setAccountBalance(transferAccount.getAccountBalance() - amountToTransfer);
//        this.accountService.updateBankAccount(transferAccount);
//        this.accountService.updateBankAccount(receiverAccount);


    }
}
