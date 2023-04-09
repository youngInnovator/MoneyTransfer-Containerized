package com.wut.money.transfer;

import com.wut.money.transfer.service.AccountService;
import com.wut.money.transfer.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyTransferApplication  {

    public MoneyTransferApplication(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
          }

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
////        Customer customer = new Customer();
////        customer.setFirstName("MUHAMMAD");
////        customer.setLastName("AFZAL");
////        customer.setEmail("afzal@gmail.com");
////        customer.setPhone("+48729294512");
////        customer.setPassword("123456");
////        customer.setPin("1122");
////
////        Account account1 = new Account();
////        account1.setAccountType("Saving");
////        account1.setAccountBalance(200);
////        account1.setCreditAmount(0);
////        account1.setDebitAmount(0);
////        account1.setCustomers(customer);
////
////        Account account2 = new Account();
////        account2.setAccountType("Current");
////        account2.setAccountBalance(400);
////        account2.setCreditAmount(0);
////        account2.setDebitAmount(0);
////        account2.setCustomers(customer);
////
////
////        List<Account> accountList = new ArrayList<>();
////        accountList.add(account1);
////        accountList.add(account2);
////        customer.setAccount(accountList);
////
////        Customer saveCustomer = this.customerService.saveCustomer(customer);
////
////        List<Account> accounts = saveCustomer.getAccount();
////
////        for(Account account:accounts){
////            System.out.println(account.getAccountType());
////            System.out.println(account.getAccountBalance());
////        }
//
//
//
//        UserOrder userOrder = new UserOrder();
//        userOrder.setAccountBalance(1000);
//        userOrder.setCreditAmount(100);
//        userOrder.setDebitAmount(200);
//        Account account = this.accountService.getAccountById(42421001);
//        userOrder.setAccounts(account);
//
//        UserOrder saveUserOrder = this.orderService.saveOrder(userOrder);
//
//        UserOrder userOrder1 = new UserOrder();
//        userOrder1.setAccountBalance(1000);
//        userOrder1.setCreditAmount(100);
//        userOrder1.setDebitAmount(200);
//        userOrder1.setAccounts(account);
//
//        UserOrder saveUserOrder1 = this.orderService.saveOrder(userOrder1);
//
//    }

    final
    CustomerService customerService;

    final
    AccountService accountService;



}
