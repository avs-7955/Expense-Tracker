package com.kyra.Expense.Tracker.exceptions;

import com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes;
import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
    
    private final ErrorCodes errorCode;
    
    protected BaseException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    protected BaseException(String message, ErrorCodes errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}

