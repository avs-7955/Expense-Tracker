package com.kyra.Expense.Tracker.db;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_categories_user_name", columnNames = {"user_id", "name"})
        }
)
public class Category extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(length = 100)
    private String icon;

    @Column(nullable = false)
    @Builder.Default
    private boolean systemGenerated = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    /**
     * User who owns this category.
     * <p>
     * For system categories (default ones), this will be NULL.
     * For user-created categories, this will be the user ID.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

