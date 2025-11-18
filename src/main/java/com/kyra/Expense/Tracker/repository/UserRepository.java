package com.kyra.Expense.Tracker.repository;

import com.kyra.Expense.Tracker.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByReferenceId(UUID referenceId);
}

