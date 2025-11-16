package com.kyra.Expense.Tracker.exceptions;

import static com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes.CONFLICT;

public class ConflictException extends BaseException {
    
    public ConflictException(String message) {
        super(message, CONFLICT);
    }
    
    public ConflictException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue),
                CONFLICT);
    }
}

