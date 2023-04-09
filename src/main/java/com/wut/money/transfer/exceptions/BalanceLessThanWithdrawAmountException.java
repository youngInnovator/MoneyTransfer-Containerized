package com.wut.money.transfer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BalanceLessThanWithdrawAmountException extends RuntimeException{
    public BalanceLessThanWithdrawAmountException(){
        super("Account balance is less than amount");
    }

}
