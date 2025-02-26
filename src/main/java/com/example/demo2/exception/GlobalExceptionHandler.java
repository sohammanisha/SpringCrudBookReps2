package com.example.demo2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import com.example.demo2.exception.AIServiceException;
@RestControllerAdvice
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	// Handle Validation Errors (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }	
    /*
    // Handle Constraint Violation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleContraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> 
            errors.put(error., error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
    */
    // Handle Book Not Found (404)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(ResponseStatusException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
    // Handle AI API Errors
    @ExceptionHandler(AIServiceException.class)
    public ResponseEntity<Map<String, String>> handleAIServiceException(AIServiceException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "AI service failure: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }
 // Handle All other Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllOtherException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Any other service failure: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

}
