package com.kyra.Expense.Tracker.service.impl;

import com.kyra.Expense.Tracker.converters.ExpenseMapper;
import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.db.Expense;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.dto.ExpenseDTO;
import com.kyra.Expense.Tracker.exceptions.ResourceNotFoundException;
import com.kyra.Expense.Tracker.repository.ExpenseRepository;
import com.kyra.Expense.Tracker.service.CategoryService;
import com.kyra.Expense.Tracker.service.ExpenseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final CategoryService categoryService;

    @Transactional(readOnly = true)
    @Override
    public List<ExpenseDTO> getAllExpensesForUser() {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (AuthenticationException ex) {
            throw new ResourceNotFoundException("LoggedIn User was not found");
        }

        List<Expense> expenses = expenseRepository.findAllByUserOrderByExpenseDateDesc(user);
        return expenseMapper.toDTOs(expenses);
    }

    @Transactional
    @Override
    public ExpenseDTO createExpense(@NonNull Expense expense) {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (AuthenticationException ex) {
            throw new ResourceNotFoundException("LoggedIn User was not found");
        }

        // Get category by referenceId
        Category category = categoryService.getCategoryByReferenceId(expense.getCategory().getReferenceId());

        expense.setUser(user);
        expense.setCategory(category);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toDTO(savedExpense);
    }

    @Transactional
    @Override
    public ExpenseDTO updateExpense(@NonNull UUID referenceId, @NonNull Expense expenseUpdate) {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (AuthenticationException ex) {
            throw new ResourceNotFoundException("LoggedIn User was not found");
        }

        // Find existing expense
        Expense existingExpense = expenseRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "referenceId", referenceId));

        // Verify expense belongs to the authenticated user
        if (!Objects.equals(existingExpense.getUser().getId(), user.getId())) {
            throw new ResourceNotFoundException("Expense", "referenceId", referenceId);
        }

        // Update fields if provided
        if (Objects.nonNull(expenseUpdate.getAmount())) {
            existingExpense.setAmount(expenseUpdate.getAmount());
        }
        if (Objects.nonNull(expenseUpdate.getExpenseDate())) {
            existingExpense.setExpenseDate(expenseUpdate.getExpenseDate());
        }
        if (Objects.nonNull(expenseUpdate.getDescription())) {
            existingExpense.setDescription(expenseUpdate.getDescription());
        }
        if (Objects.nonNull(expenseUpdate.getPaymentMethod())) {
            existingExpense.setPaymentMethod(expenseUpdate.getPaymentMethod());
        }

        // Update category if provided
        if (Objects.nonNull(expenseUpdate.getCategory()) &&
                Objects.nonNull(expenseUpdate.getCategory().getReferenceId())) {
            Category category = categoryService.getCategoryByReferenceId(
                    expenseUpdate.getCategory().getReferenceId());
            existingExpense.setCategory(category);
        }

        Expense updatedExpense = expenseRepository.save(existingExpense);
        return expenseMapper.toDTO(updatedExpense);
    }
}
