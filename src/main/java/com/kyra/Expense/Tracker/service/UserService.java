package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.db.User;
import lombok.NonNull;

import java.util.UUID;

public interface UserService {
    User getUserByReferenceId(@NonNull UUID referenceId);

    User getUserByEmail(@NonNull String email);

    User createUser(@NonNull User user);
}
