package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.db.User;

public interface SessionService {

    void createSession(User user, String refreshToken);

    void validateSession(String refreshToken);

    void deleteSession(String refreshToken);
}
