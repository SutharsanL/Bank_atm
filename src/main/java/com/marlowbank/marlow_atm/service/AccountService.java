package com.marlowbank.marlow_atm.service;

import com.marlowbank.marlow_atm.model.Account;
import com.marlowbank.marlow_atm.model.AccountUser;
import com.marlowbank.marlow_atm.model.AccountUserId;
import com.marlowbank.marlow_atm.model.User;
import com.marlowbank.marlow_atm.repository.AccountRepository;
import com.marlowbank.marlow_atm.repository.AccountUserRepository;
import com.marlowbank.marlow_atm.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountUserRepository accountUserRepository;

    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public void addUserToAccount(Long accountId, Long userId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AccountUser accountUser = new AccountUser();
        accountUser.setId(AccountUserId.builder()
                .accountId(accountId)
                .userId(userId)
                .build());
        accountUser.setAccount(account);
        accountUser.setUser(user);

        accountUserRepository.save(accountUser);
    }
}
