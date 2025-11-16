package com.kyra.Expense.Tracker.utils;

import com.kyra.Expense.Tracker.advice.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    
    private ResponseHandler() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Creates a successful response with data
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }
    
    /**
     * Creates a successful response with data and message
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        return ResponseEntity.ok(ApiResponse.success(data, message));
    }
    
    /**
     * Creates a successful response with only a message
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        return ResponseEntity.ok(ApiResponse.success(message));
    }
    
    /**
     * Creates a successful response with custom HTTP status
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponse.success(data));
    }
    
    /**
     * Creates a successful response with data, message, and custom HTTP status
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponse.success(data, message));
    }
    
    /**
     * Creates a created response (201) with data
     */
    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(data));
    }
    
    /**
     * Creates a created response (201) with data and message
     */
    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(data, message));
    }
    
    /**
     * Creates a no content response (204)
     */
    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    /**
     * Creates an error response
     */
    public static <T> ResponseEntity<ApiResponse<T>> error(String message, String code, HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponse.error(message, code));
    }
}

