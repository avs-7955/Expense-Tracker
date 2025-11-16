package com.kyra.Expense.Tracker.exceptions;

import static com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes.BAD_REQUEST;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message, BAD_REQUEST);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, BAD_REQUEST, cause);
    }
}

