package com.kyra.Expense.Tracker.dto;

import com.kyra.Expense.Tracker.enums.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCreateDTO {

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Amount must have at most 10 integer digits and 2 decimal places")
    private BigDecimal amount;

    @NotNull
    @PastOrPresent(message = "Expense date cannot be in the future")
    private LocalDate expenseDate;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private UUID categoryReferenceId;
}
