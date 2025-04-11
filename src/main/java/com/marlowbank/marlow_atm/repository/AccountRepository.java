package com.marlowbank.marlow_atm.repository;

import com.marlowbank.marlow_atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    List<Account> findByUsersId(Long userId);
}
