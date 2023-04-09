package com.wut.money.transfer.service.implementation;

import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.Customer;
import com.wut.money.transfer.entity.UserTransactions;
import com.wut.money.transfer.enums.CurrencyEnum;
import com.wut.money.transfer.exceptions.BalanceLessThanWithdrawAmountException;
import com.wut.money.transfer.repository.AccountRepository;
import com.wut.money.transfer.repository.CustomerRepository;
import com.wut.money.transfer.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Account createNewAccount(Account account){
        Customer customer = account.getCustomers();
//        Account accountToSave = new Account();
        if (customer == null) {
            account.setCustomers(null);
        }
//        if(account.getAccountCurrency().equals("PLN")){
//            accountToSave.setAccountCurrency(CurrencyEnum.PLN);
//        } else if (account.getAccountCurrency().equals("USD")) {
//            accountToSave.setAccountCurrency(CurrencyEnum.USD);
//        } else if (account.getAccountCurrency().equals("EUR")) {
//            accountToSave.setAccountCurrency(CurrencyEnum.EUR);
//        }

//        Account account1 = this.accountRepository.findById(account)
//        return this.accountRepository.save(account);
//        account.setAccountBalance(account.getAccountBalance());

        return  this.accountRepository.save(account);

    }
    @Override
    public Account getAccountById(int accountId){
        Optional<Account> account = this.accountRepository.findById(accountId);
        if(account.isPresent()){
            return account.get();
        }else {
            return null;
        }
    }

    @Override
    public List<Account> getAllAccount() {
        return this.accountRepository.findAll();
    }

    @Override
    public List<Account> getAccountStatement(int accountId) {
         return  this.accountRepository.findAllById(Collections.singleton(accountId));
    }


    @Override
    public double getAccountBalance(int accountId) {
        Optional<Account> optionalAccount = this.accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            double amount;
            amount = account.getAccountBalance();
            return amount;
        }else{
            return 0.0;
        }
    }

    @Override
    public Account updateBankAccount(Account account) {
        Optional<Account> optionalAccount = this.accountRepository.findById(account.getAccountId());
        if(optionalAccount.isPresent()){
            Account updateAccount = optionalAccount.get();

            updateAccount.setAccountBalance(account.getAccountBalance());
            updateAccount.setAccountCurrency(account.getAccountCurrency());

            List<UserTransactions> transactions = account.getTransactionsList();
            if (transactions.isEmpty()){
                updateAccount.setTransactionsList(account.getTransactionsList());
            }else {
                updateAccount.setTransactionsList(transactions);
            }
            updateAccount.setCustomers(account.getCustomers());
            this.accountRepository.save(updateAccount);
            return updateAccount;
        }else {
            return null;
        }
    }

    @Override
    public Account setUserAccount(int customerId, int accountId) {
        Optional<Account> optionalAccount = this.accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();

            Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
            if(optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
                account.setCustomers(customer);
                this.accountRepository.save(account);
                return account;
            }else {
                return null;
            }

        }else {
            return null;
        }

    }

    @Override
    public Account deleteAccountById(int accountId) {
        Optional<Account> account = this.accountRepository.findById(accountId);
        if(account.isPresent()){
            this.accountRepository.deleteById(accountId);
            return account.get();
        }else {
            return null;
        }
    }

    public Account depositMoney(double amount, int accountId){
        Optional<Account> optionalAccount = this.accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            double accountBalance = account.getAccountBalance();
            accountBalance += amount;

            account.setAccountBalance(accountBalance);
            this.accountRepository.save(account);

            return account;
        }else {
            return null;
        }

    }

    @Override
    public Account withdrawalAccount(double amount, int accountId){
       Optional<Account> optionalAccount = this.accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            double accountBalance = account.getAccountBalance();
            if(accountBalance >= amount){
                accountBalance -= amount;
            }else{
                throw new BalanceLessThanWithdrawAmountException();
            }

            account.setAccountBalance(accountBalance);

            this.accountRepository.save(account);

            return account;
        }else {
            return null;
        }

    }

}
