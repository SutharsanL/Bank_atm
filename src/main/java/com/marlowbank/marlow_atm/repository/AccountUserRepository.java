package com.marlowbank.marlow_atm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marlowbank.marlow_atm.model.AccountUser;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
    @Query("SELECT COUNT(au) > 0 FROM AccountUser au WHERE au.id.accountId = :accountId AND au.id.userId = :userId")
    boolean existsByAccountIdAndUserId(@Param("accountId") Long accountId, @Param("userId") Long userId);
}
