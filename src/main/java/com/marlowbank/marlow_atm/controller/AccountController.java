package com.marlowbank.marlow_atm.controller;

import com.marlowbank.marlow_atm.model.Account;
import com.marlowbank.marlow_atm.model.User;
import com.marlowbank.marlow_atm.repository.AccountRepository;
import com.marlowbank.marlow_atm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{accountId}/users/{userId}")
    public ResponseEntity<String> addUserToAccount(@PathVariable Long accountId, @PathVariable Long userId) {
        accountService.addUserToAccount(accountId, userId);
        return ResponseEntity.ok("User added to account successfully.");
    }
}
