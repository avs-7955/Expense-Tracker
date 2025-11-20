package com.kyra.Expense.Tracker.service.impl;

import com.kyra.Expense.Tracker.converters.CategoryMapper;
import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.dto.CategoryDTO;
import com.kyra.Expense.Tracker.repository.CategoryRepository;
import com.kyra.Expense.Tracker.service.CategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserServiceImpl userService;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllCategoriesForUser(@NonNull UUID userReferenceId) {
        User user = userService.getUserByReferenceId(userReferenceId);

        List<Category> categories = new ArrayList<>();
        categories.addAll(categoryRepository.findAllBySystemGeneratedTrue());
        categories.addAll(categoryRepository.findAllByUser(user));
        categories.sort(Comparator.comparing(Category::getName, String.CASE_INSENSITIVE_ORDER));

        return categoryMapper.toDTOs(categories);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllSystemGeneratedCategories() {
        List<Category> categories = categoryRepository.findAllBySystemGeneratedTrue();
        categories.sort(Comparator.comparing(Category::getName, String.CASE_INSENSITIVE_ORDER));
        return categoryMapper.toDTOs(categories);
    }
}

