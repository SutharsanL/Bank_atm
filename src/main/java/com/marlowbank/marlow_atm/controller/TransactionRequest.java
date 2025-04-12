package com.marlowbank.marlow_atm.controller;

import lombok.Data;


@Data
public class TransactionRequest {
    // Getters and setters
    private Long accountId;
    private double amount;

}
