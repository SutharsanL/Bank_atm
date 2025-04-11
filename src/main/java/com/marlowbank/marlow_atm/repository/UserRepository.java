package com.marlowbank.marlow_atm.repository;

import com.marlowbank.marlow_atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//check
@Query("SELECT u FROM User u LEFT JOIN FETCH u.accountUsers WHERE u.id = :userId")
Optional<User> findByIdWithAccountUsers(@Param("userId") Long userId);

}
