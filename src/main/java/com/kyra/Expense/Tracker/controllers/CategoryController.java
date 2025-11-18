package com.kyra.Expense.Tracker.controllers;

import com.kyra.Expense.Tracker.dto.CategoryDTO;
import com.kyra.Expense.Tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/user/{referenceId}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesForUser(@PathVariable("referenceId") UUID referenceId) {
        return ResponseEntity.ok(categoryService.getAllCategoriesForUser(referenceId));
    }
}

