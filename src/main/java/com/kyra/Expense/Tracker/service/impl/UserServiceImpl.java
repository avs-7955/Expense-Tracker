package com.kyra.Expense.Tracker.service.impl;

import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.exceptions.ResourceNotFoundException;
import com.kyra.Expense.Tracker.repository.UserRepository;
import com.kyra.Expense.Tracker.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User getUserByReferenceId(@NonNull UUID referenceId) {
        return userRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "referenceId", referenceId));
    }

    @Override
    public User getUserByEmail(@NonNull String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User createUser(@NonNull User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }
}

