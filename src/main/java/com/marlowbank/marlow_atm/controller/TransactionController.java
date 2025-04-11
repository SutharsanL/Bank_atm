package com.marlowbank.marlow_atm.controller;

import com.marlowbank.marlow_atm.model.Transaction;
import com.marlowbank.marlow_atm.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam Long accountId, @RequestParam double amount) {
        Transaction transaction = transactionService.deposit(accountId, amount);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam Long accountId, @RequestParam double amount) {
        Transaction transaction = transactionService.withdraw(accountId, amount);
        return ResponseEntity.ok(transaction);
    }
}
