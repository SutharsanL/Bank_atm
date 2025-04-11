package com.marlowbank.marlow_atm.repository;

import com.marlowbank.marlow_atm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId); // Custom query to find transactions by account ID

}
