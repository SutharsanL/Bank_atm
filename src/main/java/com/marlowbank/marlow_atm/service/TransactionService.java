package com.marlowbank.marlow_atm.service;

import com.marlowbank.marlow_atm.exception.AccountNotFoundException;
import com.marlowbank.marlow_atm.exception.InsufficientFundsException;
import com.marlowbank.marlow_atm.model.Account;
import com.marlowbank.marlow_atm.model.Transaction;
import com.marlowbank.marlow_atm.model.TransactionType;
import com.marlowbank.marlow_atm.repository.AccountRepository;
import com.marlowbank.marlow_atm.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .build();

        return transactionRepository.save(transaction);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.WITHDRAWAL)
                .build();

        return transactionRepository.save(transaction);
    }
}
