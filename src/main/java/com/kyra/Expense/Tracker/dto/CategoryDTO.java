package com.kyra.Expense.Tracker.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private UUID referenceId;

    private String name;
    private String description;

    private String icon;

    private boolean systemGenerated;
    private boolean active;
}
