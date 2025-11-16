package com.kyra.Expense.Tracker.exceptions;

import lombok.Getter;

import java.util.List;

import static com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes.VALIDATION_ERROR;

@Getter
public class ValidationException extends BaseException {
    
    private final List<String> validationErrors;
    
    public ValidationException(String message) {
        super(message, VALIDATION_ERROR);
        this.validationErrors = null;
    }
    
    public ValidationException(String message, List<String> validationErrors) {
        super(message, VALIDATION_ERROR);
        this.validationErrors = validationErrors;
    }

}

