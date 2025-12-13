package com.kyra.Expense.Tracker.repository;

import com.kyra.Expense.Tracker.db.Category;
import com.kyra.Expense.Tracker.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUser(User user);

    List<Category> findAllBySystemGeneratedTrue();

    boolean existsByUserAndName(User user, String name);
}

