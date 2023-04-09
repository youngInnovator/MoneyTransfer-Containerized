package com.wut.money.transfer.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerLogin {

    private int customerId;
    private String customerPassword;
    private String firstName;
    private String lastName;

}
