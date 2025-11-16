package com.kyra.Expense.Tracker.db;

import com.kyra.Expense.Tracker.enums.PaymentMethod;
import com.kyra.Expense.Tracker.utils.NamespaceUUID;
import com.kyra.Expense.Tracker.utils.UUIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "expenses")
public class Expense extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate expenseDate;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Override
    protected UUID generateUUID() {
        if (Objects.nonNull(description) &&
                Objects.nonNull(user) &&
                Objects.nonNull(category) &&
                Objects.nonNull(expenseDate) &&
                Objects.nonNull(amount)) {

            return UUIDGenerator.generate(
                    NamespaceUUID.EXPENSE,
                    description.trim(),
                    user.getReferenceId().toString(),
                    category.getReferenceId().toString(),
                    expenseDate.toString(),
                    amount.toString()
            );
        }
        return null;
    }

    @PrePersist
    @PreUpdate
    private void normalizeDescription() {
        if (Objects.nonNull(description)) {
            description = description.trim();
        }
    }
}

