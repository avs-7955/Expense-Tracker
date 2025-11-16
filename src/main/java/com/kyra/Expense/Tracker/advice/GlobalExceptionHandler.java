package com.kyra.Expense.Tracker.advice;

import com.kyra.Expense.Tracker.enums.exceptions.ErrorCodes;
import com.kyra.Expense.Tracker.exceptions.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        ApiError error = ApiError.of(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequestException(BadRequestException ex) {
        logger.error("Bad request: {}", ex.getMessage());
        ApiError error = ApiError.of(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(ValidationException ex) {
        logger.error("Validation error: {}", ex.getMessage());
        ApiError.ApiErrorBuilder errorBuilder = ApiError.builder()
                .code(ex.getErrorCode().name())
                .message(ex.getMessage());

        if (CollectionUtils.isNotEmpty(ex.getValidationErrors())) {
            errorBuilder.validationErrors(ex.getValidationErrors());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorBuilder.build()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Object>> handleConflictException(ConflictException ex) {
        logger.error("Conflict: {}", ex.getMessage());
        ApiError error = ApiError.of(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        logger.error("Validation error: {}", ex.getMessage());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiError error = ApiError.builder()
                .code(ErrorCodes.VALIDATION_ERROR.name())
                .message("Validation failed")
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(
            ConstraintViolationException ex) {
        logger.error("Constraint violation: {}", ex.getMessage());

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        ApiError error = ApiError.builder()
                .code(ErrorCodes.VALIDATION_ERROR.name())
                .message("Constraint validation failed")
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        logger.error("Type mismatch: {}", ex.getMessage());

        String message = String.format("Parameter '%s' should be of type %s",
                ex.getName(),
                Objects.nonNull(ex.getRequiredType()) ? ex.getRequiredType().getSimpleName() : "unknown");

        ApiError error = ApiError.of(ErrorCodes.TYPE_MISMATCH, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        logger.error("Message not readable: {}", ex.getMessage());

        ApiError error = ApiError.of(ErrorCodes.INVALID_REQUEST_BODY,
                "Request body is invalid or malformed",
                ex.getMostSpecificCause().getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Illegal argument: {}", ex.getMessage());

        ApiError error = ApiError.of(ErrorCodes.ILLEGAL_ARGUMENT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        logger.error("Unexpected error occurred", ex);

        ApiError error = ApiError.of(ErrorCodes.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(error));
    }
}

