package com.wut.money.transfer.controller;

import com.wut.money.transfer.dto.TransferRequestDTO;
import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.UserTransactions;
import com.wut.money.transfer.service.AccountService;
import com.wut.money.transfer.service.UserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserTransactionsController {

    private final UserTransactionsService transactionsService;

    private final AccountService accountService;


    public UserTransactionsController(@Qualifier("userTransactionsServiceImpl") UserTransactionsService transactionsService, AccountService accountService) {
        this.transactionsService = transactionsService;
        this.accountService = accountService;
    }


    @PostMapping("/transferFund")
    public ResponseEntity<Void> transferAmountToOtherAccount(@RequestBody TransferRequestDTO request) {
        this.transactionsService.transferAmountToOtherAccount(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<UserTransactions>> getAllTransaction(){
        List<UserTransactions>  transactionsList  = this.transactionsService.getAllTransaction();
        return ResponseEntity.of(Optional.of(transactionsList));
    }
    @GetMapping("/getTransactionById/{transactionId}")
    public ResponseEntity<UserTransactions> getTransactionById(@PathVariable int transactionId){
        UserTransactions userTransactions = this.transactionsService.getTransactionById(transactionId);
        return ResponseEntity.of(Optional.of(userTransactions));
    }

    @GetMapping("/transactionByAccount/{accountId}")
    public ResponseEntity<List<UserTransactions>> getAllTransactionByAccount(@PathVariable int accountId) {
        List<UserTransactions> transactions = this.transactionsService.getAllTransactionByAccount(accountId);
        return ResponseEntity.of(Optional.of(transactions));
    }

    @GetMapping("/transactionHistory/{accountId}")
    public ResponseEntity<List<UserTransactions>> getAccountTransactionHistory(@PathVariable int accountId) {
        List<UserTransactions> transactions = this.transactionsService.getAccountTransactionHistory(accountId);
        return ResponseEntity.of(Optional.of(transactions));
    }

    @GetMapping("/allDebitedAmount/{accountId}")
    public ResponseEntity<List<UserTransactions>> getAllDebitedAmount(@PathVariable int accountId){
        List<UserTransactions> transactions = this.transactionsService.getAllDebitedAmount(accountId);
        return ResponseEntity.of(Optional.of(transactions));
    }
    @GetMapping("/allCreditedAmount/{accountId}")
    public ResponseEntity<List<UserTransactions>> getAllCreditedAmount(@PathVariable int accountId){
        List<UserTransactions> transactions = this.transactionsService.getAllCreditedAmount(accountId);
        return ResponseEntity.of(Optional.of(transactions));
    }









}
