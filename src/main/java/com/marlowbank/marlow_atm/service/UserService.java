package com.marlowbank.marlow_atm.service;

import com.marlowbank.marlow_atm.model.User;
import com.marlowbank.marlow_atm.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserWithAccountUsers(Long userId) {
        return userRepository.findByIdWithAccountUsers(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
