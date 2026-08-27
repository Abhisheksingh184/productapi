package com.abhishek.productapi.exception;

import com.abhishek.productapi.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author siabhis
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ErrorResponse response= new ErrorResponse("Validation Failed", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        Map<String,String> errors = new HashMap<>();
        errors.put("Product", e.getMessage());
        ErrorResponse response= new ErrorResponse("Product not found", errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String,String> errors = new HashMap<>();
        e.getConstraintViolations().forEach((error) -> {
            errors.put(error.getPropertyPath().toString(), error.getMessage());

        });
        ErrorResponse response= new ErrorResponse("Validation Failed", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
