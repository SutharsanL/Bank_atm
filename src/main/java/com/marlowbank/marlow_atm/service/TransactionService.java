package com.marlowbank.marlow_atm.service;

import com.marlowbank.marlow_atm.dto.TransactionResponse;
import com.marlowbank.marlow_atm.exception.AccountNotFoundException;
import com.marlowbank.marlow_atm.exception.InsufficientFundsException;
import com.marlowbank.marlow_atm.exception.UnAuthorizedUserException;
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
    public TransactionResponse deposit(Long accountId, double amount,Long userId) {
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = transactionRepository.save(
                Transaction.builder()
                        .account(account)
                        .amount(amount)
                        .type(TransactionType.DEPOSIT)
                        .userId(userId)
                        .build()
        );

        return TransactionResponse.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .updatedBalance(account.getBalance())
                .timestamp(transaction.getTransactionDate())
                .build();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransactionResponse withdraw(Long accountId, double amount,Long userId) {
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        // Check if the user is associated with the account
        boolean isAuthorized = account.getAccountUsers().stream()
                .anyMatch(accountUser -> accountUser.getUser().getId().equals(userId));

        if (!isAuthorized) {
            throw new UnAuthorizedUserException("User is not authorized to access this account");
        }

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);


        Transaction transaction = transactionRepository.save(
                Transaction.builder()
                        .account(account)
                        .amount(amount)
                        .type(TransactionType.WITHDRAWAL)
                        .userId(userId)
                        .build()
        );

        return  TransactionResponse.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .updatedBalance(account.getBalance())
                .timestamp(transaction.getTransactionDate())
                .build();
    }
}
