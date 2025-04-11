package com.marlowbank.marlow_atm.service;

import org.springframework.stereotype.Service;
import com.marlowbank.marlow_atm.model.Account;
import com.marlowbank.marlow_atm.model.AccountUser;
import com.marlowbank.marlow_atm.model.AccountUserId;
import com.marlowbank.marlow_atm.model.User;
import com.marlowbank.marlow_atm.repository.AccountRepository;
import com.marlowbank.marlow_atm.repository.AccountUserRepository;
import com.marlowbank.marlow_atm.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountUserService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountUserRepository accountUserRepository;

    public AccountUserService(AccountRepository accountRepository, UserRepository userRepository, AccountUserRepository accountUserRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountUserRepository = accountUserRepository;
    }

    @Transactional
    public void associateUserWithAccount(Long userId, Long accountId) {
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

        account.getAccountUsers().add(accountUser);
        user.getAccountUsers().add(accountUser);

        accountUserRepository.save(accountUser);
    }
}
