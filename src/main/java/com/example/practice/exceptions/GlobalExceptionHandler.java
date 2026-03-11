package com.example.practice.exceptions;


import com.example.practice.product.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
 // more exceptions are added here
    @ExceptionHandler(ProductNotFoundException.class)
 public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception){
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage(),HttpStatus.NOT_FOUND.toString()));
 }

 @ExceptionHandler(ProductNotValidException.class)
    public ResponseEntity<ErrorResponse> handleProductNotValidException(ProductNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage(),HttpStatus.BAD_GATEWAY.toString()));
 }

 @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage(),HttpStatus.BAD_REQUEST.toString())));
 }

}
