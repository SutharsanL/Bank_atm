package com.marlowbank.marlow_atm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marlowbank.marlow_atm.model.AccountUser;
import com.marlowbank.marlow_atm.model.AccountUserId;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
}
