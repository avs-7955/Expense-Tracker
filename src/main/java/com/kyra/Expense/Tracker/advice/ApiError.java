package com.kyra.Expense.Tracker.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private String code;
    private String message;
    private String details;
    private List<String> validationErrors;
    private Map<String, Object> metadata;

    public static ApiError of(ErrorCodes code, String message) {
        return ApiError.builder()
                .code(code.name())
                .message(message)
                .build();
    }

    public static ApiError of(ErrorCodes code, String message, String details) {
        return ApiError.builder()
                .code(code.name())
                .message(message)
                .details(details)
                .build();
    }
}

