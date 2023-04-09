package com.wut.money.transfer.controller;


import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.Customer;
import com.wut.money.transfer.service.AccountService;
import com.wut.money.transfer.service.CustomerService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController("")
public class AccountController {


    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @PostMapping("/saveAccount/customerId/{customerId}")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account,@PathVariable int customerId){
        Customer customer = this.customerService.findCustomerById(customerId);
        account.setCustomers(customer);
        Account  account1 = this.accountService.createNewAccount(account);
        return ResponseEntity.of(Optional.of(account1));
    }


    @GetMapping("/getAccount/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable int accountId){
        List<Account> accountList  = this.accountService.getAllAccount();
        Account account = new Account();
        for (Account account1 : accountList) {
            if (account1.getAccountId() == accountId){
                account = account1;
            }
        }

////        Account  account = this.accountService.getAccountById(accountId);
//        Customer customer= account.getCustomers();
//        System.out.println(customer);
//        return ResponseEntity.of(Optional.of(account));
        return ResponseEntity.of(Optional.of(account));
    }

    @GetMapping("/getAllAccount")
    public ResponseEntity<List<Account>> getAllAccount(){
        List<Account> accountList  = this.accountService.getAllAccount();
        return ResponseEntity.of(Optional.of(accountList));
    }

    @GetMapping("/getAccountStatement")
    public ResponseEntity<List<Account>> getAccountStatement(int accountId){
        List<Account> accountList  = this.accountService.getAccountStatement(accountId);
        return ResponseEntity.of(Optional.of(accountList));
    }

    @GetMapping("/getAccountBalance/{accountId}")
    public ResponseEntity<Map<String, Double>> getAccountBalance(@PathVariable int accountId){
        List<Account> accountList  = this.accountService.getAllAccount();
        Account account = new Account();
        double acctBalance = 0.0;
        for (Account account1 : accountList) {
            if (account1.getAccountId() == accountId){
                account = account1;
                acctBalance = account1.getAccountBalance();
            }
        }

        Map<String, Double> accountBalance = new HashMap<>();
        accountBalance.put("Account Balance", acctBalance);
        return ResponseEntity.of(Optional.of(accountBalance));
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<Account> updateBankAccount(@RequestBody Account account){
        Account account1 = this.accountService.updateBankAccount(account);
        return ResponseEntity.of(Optional.of(account1));
    }
    @PutMapping("/setUserAccount/customerId/{customerId}/accountId/{accountId}")
   public ResponseEntity<Account> setUserAccount(@PathVariable int customerId, @PathVariable int accountId){
        Account account =  this.accountService.setUserAccount(customerId, accountId);
        return ResponseEntity.of(Optional.of(account));
    }

    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable int accountId){
        Account account = this.accountService.deleteAccountById(accountId);
        return ResponseEntity.of(Optional.of(account));
    }


    @PutMapping("/depositAmount/amount/{amount}/accountId/{accountId}")
    public ResponseEntity<Account> depositMoney(@PathVariable double amount, @PathVariable int accountId){
      Account account=  this.accountService.depositMoney(amount,accountId);
      return ResponseEntity.of(Optional.of(account));
    }

    @PutMapping("/withdrawalMoney/amount/{amount}/accountId/{accountId}")
    public ResponseEntity<Account> withdrawalAccount(@PathVariable double amount, @PathVariable int accountId){
        Account account=  this.accountService.withdrawalAccount(amount,accountId);
        return ResponseEntity.of(Optional.of(account));
    }


    public AccountService getAccountService() {
        return accountService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
}
