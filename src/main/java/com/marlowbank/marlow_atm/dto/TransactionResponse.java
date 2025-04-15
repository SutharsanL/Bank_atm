package com.marlowbank.marlow_atm.dto;

import com.marlowbank.marlow_atm.model.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TransactionResponse {
    private Long transactionId;
    private Long accountId;
    private double amount;
    private TransactionType type;
    private double updatedBalance;
    private Timestamp timestamp;
}

