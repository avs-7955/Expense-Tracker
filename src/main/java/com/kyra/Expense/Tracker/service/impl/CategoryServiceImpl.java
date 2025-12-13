package com.kyra.Expense.Tracker.service.impl;

import com.kyra.Expense.Tracker.converters.CategoryMapper;
import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.db.User;
import com.kyra.Expense.Tracker.dto.CategoryDTO;
import com.kyra.Expense.Tracker.exceptions.ConflictException;
import com.kyra.Expense.Tracker.exceptions.ResourceNotFoundException;
import com.kyra.Expense.Tracker.repository.CategoryRepository;
import com.kyra.Expense.Tracker.service.CategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllCategoriesForUser() {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (AuthenticationException ex) {
            throw new ResourceNotFoundException("LoggedIn User was not found");
        }

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

    @Transactional
    @Override
    public CategoryDTO createCategory(@NonNull Category category) {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (AuthenticationException ex) {
            throw new ResourceNotFoundException("LoggedIn User was not found");
        }
        // Check if category with same name already exists for this user
        if (categoryRepository.existsByUserAndName(user, category.getName())) {
            throw new ConflictException("Category", "name", category.getName());
        }

        category.setUser(user);
        category.setSystemGenerated(false);
        category.setActive(true);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryByReferenceId(@NonNull UUID referenceId) {
        return categoryRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "referenceId", referenceId));
    }
}

