package com.kyra.Expense.Tracker.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

