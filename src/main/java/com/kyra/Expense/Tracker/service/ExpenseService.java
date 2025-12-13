package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.db.Expense;
import com.kyra.Expense.Tracker.dto.ExpenseDTO;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    List<ExpenseDTO> getAllExpensesForUser();

    ExpenseDTO createExpense(@NonNull Expense expense);

    ExpenseDTO updateExpense(@NonNull UUID referenceId, @NonNull Expense expense);
}
