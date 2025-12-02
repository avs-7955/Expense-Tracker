package com.kyra.Expense.Tracker.service.impl;

import com.kyra.Expense.Tracker.db.Session;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.repository.SessionRepository;
import com.kyra.Expense.Tracker.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;

    private final int SESSION_LIMIT = 2;

    @Override
    public void createSession(User user, String refreshToken) {
        List<Session> userSessions = repository.findByUser(user);
        if (userSessions.size() == SESSION_LIMIT) {
            repository.deleteLeastRecentlyUsedSession(user.getId());
        }
        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        repository.save(newSession);
    }

    @Override
    public void validateSession(String refreshToken) {
        Session session = repository.findByRefreshToken(refreshToken)
                .orElse(null);
        if (Objects.nonNull(session)) {
            session.setUpdatedAt(LocalDateTime.now());
            repository.save(session);
        } else {
            throw new SessionAuthenticationException("Invalid Session present. Please login again.");
        }
    }

    @Override
    public void deleteSession(String refreshToken) {
        Session session = repository.findByRefreshToken(refreshToken)
                .orElse(null);
        if (Objects.nonNull(session)) {
            repository.delete(session);
        } else {
            throw new SessionAuthenticationException("Invalid Session present. Please login again.");
        }
    }
}
