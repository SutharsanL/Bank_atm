package com.marlowbank.marlow_atm.controller;

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
    public ResponseEntity<Transaction> deposit(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.deposit(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.withdraw(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }
}
