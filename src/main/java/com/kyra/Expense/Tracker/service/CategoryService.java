package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.dto.CategoryDTO;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> getAllCategoriesForUser(@NonNull UUID userReferenceId);

    List<CategoryDTO> getAllSystemGeneratedCategories();
}
