package com.wut.money.transfer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(generator = "user_gen",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_gen",sequenceName = "user_seq", initialValue = 1001, allocationSize = 1 )
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String pin;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Account> account = new ArrayList<>();

    public Customer(String firstName, String lastName, String email, String phone, String password, String pin, List<Account> account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.pin = pin;
        this.account = account;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}
