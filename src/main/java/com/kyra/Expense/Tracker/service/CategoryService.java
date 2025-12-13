package com.kyra.Expense.Tracker.service;

import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.dto.CategoryDTO;
import lombok.NonNull;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategoriesForUser();

    List<CategoryDTO> getAllSystemGeneratedCategories();

    CategoryDTO createCategory(@NonNull Category category);
}
