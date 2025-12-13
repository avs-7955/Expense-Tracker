package com.kyra.Expense.Tracker.repository;

import com.kyra.Expense.Tracker.db.Expense;
import com.kyra.Expense.Tracker.db.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUserOrderByExpenseDateDesc(@NonNull User user);

    Optional<Expense> findByReferenceId(UUID referenceId);
}
