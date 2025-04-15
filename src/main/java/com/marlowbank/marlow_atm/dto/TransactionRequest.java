package com.marlowbank.marlow_atm.dto;

import lombok.Data;


@Data
public class TransactionRequest {
    // Getters and setters
    private Long accountId;
    private double amount;
    private Long userId;
}
