package com.marlowbank.marlow_atm.repository;

import com.marlowbank.marlow_atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAccountsId(Long accountId);
}
