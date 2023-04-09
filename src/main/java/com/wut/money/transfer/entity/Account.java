package com.wut.money.transfer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wut.money.transfer.enums.CurrencyEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Account  {

    @Id
    @GeneratedValue(generator = "account_gen",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "account_gen",sequenceName = "account_seq", initialValue = 410000, allocationSize = 1 )
    private int accountId;
    private double accountBalance;
    private String accountCurrency ;


    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserTransactions> transactionsList = new ArrayList<>();


    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customerId",nullable = true)
    @JsonBackReference
    private Customer customers;

    public Account(double accountBalance, String accountCurrency, List<UserTransactions> transactionsList, Customer customers) {
        this.accountBalance = accountBalance;
        this.accountCurrency = accountCurrency;
        this.transactionsList = transactionsList;
        this.customers = customers;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public List<UserTransactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<UserTransactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public Customer getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customers) {
        this.customers = customers;
    }

    public void depositAmount(double amount){
        this.accountBalance += amount;
    }
}


