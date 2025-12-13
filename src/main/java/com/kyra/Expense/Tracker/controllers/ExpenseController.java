package com.kyra.Expense.Tracker.controllers;

import com.kyra.Expense.Tracker.advice.ApiResponse;
import com.kyra.Expense.Tracker.converters.ExpenseMapper;
import com.kyra.Expense.Tracker.db.Expense;
import com.kyra.Expense.Tracker.dto.ExpenseCreateDTO;
import com.kyra.Expense.Tracker.dto.ExpenseDTO;
import com.kyra.Expense.Tracker.dto.ExpenseUpdateDTO;
import com.kyra.Expense.Tracker.service.ExpenseService;
import com.kyra.Expense.Tracker.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<ExpenseDTO>>> getExpensesForUser() {
        return ResponseHandler.success(expenseService.getAllExpensesForUser());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseDTO>> createExpense(
            @RequestBody @Valid ExpenseCreateDTO expenseCreateDTO) {
        Expense expense = expenseMapper.toEntity(expenseCreateDTO);
        ExpenseDTO createdExpense = expenseService.createExpense(expense);
        return ResponseHandler.success(createdExpense);
    }

    @PutMapping("/{referenceId}")
    public ResponseEntity<ApiResponse<ExpenseDTO>> updateExpense(
            @PathVariable("referenceId") UUID referenceId,
            @RequestBody @Valid ExpenseUpdateDTO expenseUpdateDTO) {
        Expense expense = expenseMapper.toEntity(expenseUpdateDTO);
        ExpenseDTO updatedExpense = expenseService.updateExpense(referenceId, expense);
        return ResponseHandler.success(updatedExpense);
    }
}
