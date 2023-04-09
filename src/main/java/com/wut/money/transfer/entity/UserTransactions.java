package com.wut.money.transfer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @SequenceGenerator(name = "tran-seq",sequenceName = "tran-seq",initialValue = 2000, allocationSize = 1)
    private int transactionId;
    private LocalDateTime dateTime;
    private double amount;
    private String transactionType;
    private double accountBalance;
    private  String currencyType;


    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account userAccount;


    public UserTransactions(LocalDateTime dateTime, double amount, String transactionType, double accountBalance, String currencyType, Account userAccount) {
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
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
