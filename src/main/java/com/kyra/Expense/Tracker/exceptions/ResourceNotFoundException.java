package com.kyra.Expense.Tracker.exceptions;

import static com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes.RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends BaseException {
    
    public ResourceNotFoundException(String message) {
        super(message, RESOURCE_NOT_FOUND);
    }
    
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue),
                RESOURCE_NOT_FOUND);
    }
}

