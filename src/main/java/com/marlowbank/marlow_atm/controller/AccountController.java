package com.marlowbank.marlow_atm.controller;

import com.marlowbank.marlow_atm.model.Account;
import com.marlowbank.marlow_atm.repository.AccountRepository;
import com.marlowbank.marlow_atm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping("/{accountId}/users/{userId}")
    public ResponseEntity<String> addUserToAccount(@PathVariable Long accountId, @PathVariable Long userId) {
        accountService.addUserToAccount(accountId, userId);
        return ResponseEntity.ok("User added to account successfully.");
    }
}



