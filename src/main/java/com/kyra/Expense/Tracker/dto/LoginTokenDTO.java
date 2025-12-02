package com.kyra.Expense.Tracker.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginTokenDTO {

    @NonNull
    private UUID referenceId;

    @NonNull
    private String accessToken;

    @NonNull
    private String refreshToken;
}

