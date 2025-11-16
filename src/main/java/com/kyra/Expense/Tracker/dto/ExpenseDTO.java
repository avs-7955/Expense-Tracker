package com.kyra.Expense.Tracker.dto;

import com.kyra.Expense.Tracker.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private UUID referenceId;

    private BigDecimal amount;
    private LocalDate expenseDate;

    private String description;

    private PaymentMethod paymentMethod;

    private UUID userReferenceId;
    private UUID categoryReferenceId;
}

