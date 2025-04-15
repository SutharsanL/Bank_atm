package com.marlowbank.marlow_atm.controller;

import com.marlowbank.marlow_atm.dto.TransactionRequest;
import com.marlowbank.marlow_atm.dto.TransactionResponse;
import com.marlowbank.marlow_atm.model.Transaction;
import com.marlowbank.marlow_atm.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.deposit(
                request.getAccountId(), request.getAmount(), request.getUserId());
        return ResponseEntity.ok(transactionResponse);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.withdraw(
                request.getAccountId(), request.getAmount(), request.getUserId());
        return ResponseEntity.ok(transactionResponse);
    }
}
