package com.marlowbank.marlow_atm.controller;

import com.marlowbank.marlow_atm.model.Account;
import com.marlowbank.marlow_atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;


    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }


    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}



