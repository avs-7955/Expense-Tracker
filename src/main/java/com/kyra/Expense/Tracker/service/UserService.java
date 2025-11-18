package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.exceptions.ResourceNotFoundException;
import com.kyra.Expense.Tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByReferenceId(UUID referenceId) {
        return userRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "referenceId", referenceId));
    }
}

