package com.kyra.Expense.Tracker.controllers;

import com.kyra.Expense.Tracker.advice.ApiResponse;
import com.kyra.Expense.Tracker.converters.CategoryMapper;
import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.dto.CategoryCreateDTO;
import com.kyra.Expense.Tracker.dto.CategoryDTO;
import com.kyra.Expense.Tracker.service.CategoryService;
import com.kyra.Expense.Tracker.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/user/{referenceId}")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getCategoriesForUser(
            @PathVariable("referenceId") UUID referenceId) {
        return ResponseHandler.success(categoryService.getAllCategoriesForUser(referenceId));
    }

    @GetMapping("/system")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getSystemGeneratedCategories() {
        return ResponseHandler.success(categoryService.getAllSystemGeneratedCategories());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(
            @RequestBody @Valid CategoryCreateDTO categoryCreateDTO) {
        Category category = categoryMapper.toEntity(categoryCreateDTO);
        CategoryDTO createdCategory = categoryService.createCategory(category);
        return ResponseHandler.success(createdCategory);
    }
}