package com.kyra.Expense.Tracker.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 100)
    private String icon;
}
