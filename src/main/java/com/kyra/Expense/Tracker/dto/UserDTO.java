package com.kyra.Expense.Tracker.dto;

import com.kyra.Expense.Tracker.enums.AuthProvider;
import com.kyra.Expense.Tracker.enums.Role;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID referenceId;

    private String firstName;
    private String lastName;
    private String email;

    private Role role;
    private AuthProvider provider;

    private boolean active;

    private String avatarUrl;
}

