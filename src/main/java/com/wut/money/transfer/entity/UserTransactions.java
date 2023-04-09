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
    private Account accountNumber;


    public UserTransactions(LocalDateTime dateTime, double debitedAmount, double creditedAmount, double balance, Account accountNumber) {
        this.dateTime = dateTime;
        this.debitedAmount = debitedAmount;
        this.creditedAmount = creditedAmount;
        this.balance = balance;
        this.accountNumber = accountNumber;
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

    public double getDebitedAmount() {
        return debitedAmount;
    }

    public void setDebitedAmount(double debitedAmount) {
        this.debitedAmount = debitedAmount;
    }

    public double getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(double creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Account accountNumber) {
        this.accountNumber = accountNumber;
    }
}
