package com.wut.money.transfer.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransferRequestDTO {
    private double balance;
    private int accountIdFrom;
    private int accountIdTO;
}
