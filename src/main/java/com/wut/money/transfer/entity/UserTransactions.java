package com.wut.money.transfer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserTransactions {
    @Id
    @GeneratedValue(generator = "trans-seq")
    @SequenceGenerator(name = "tran-seq",sequenceName = "tran-seq",allocationSize = 8000,initialValue = 1)
    private int transactionId;
    private LocalDateTime dateTime;
    private double amount;
    private double transactionType;
    private double accountBalance;
    private  String currencyType;


    @ManyToOne(optional = false)
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account userAccount;


    public UserTransactions(LocalDateTime dateTime, double amount, double transactionType, double accountBalance, String currencyType, Account userAccount) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.transactionType = transactionType;
        this.accountBalance = accountBalance;
        this.currencyType = currencyType;
        this.userAccount = userAccount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(double transactionType) {
        this.transactionType = transactionType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }


    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }
}
