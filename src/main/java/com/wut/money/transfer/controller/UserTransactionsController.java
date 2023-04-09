package com.wut.money.transfer.controller;

import com.wut.money.transfer.dto.TransferRequestDTO;
import com.wut.money.transfer.entity.Account;
import com.wut.money.transfer.entity.UserTransactions;
import com.wut.money.transfer.service.UserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserTransactionsController {

    public final UserTransactionsService transactionsService;

    public UserTransactionsController(@Qualifier("userTransactionsServiceImpl") UserTransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }


    @PostMapping("/transferFund")
    public ResponseEntity<Void> transferFunds(@RequestBody TransferRequestDTO request) {
        transactionsService.transferAmountToOtherAccount(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<UserTransactions>> getAllTransaction(){
        List<UserTransactions>  transactionsList  = this.transactionsService.getAllTransaction();
        return ResponseEntity.of(Optional.of(transactionsList));
    }
//
//    @PostMapping("/transferFund}")
//    public ResponseEntity<Void> transferFundsToOther(@RequestBody TransferRequestDTO request) {
//
//        return ResponseEntity.ok().build();
//    }


}
