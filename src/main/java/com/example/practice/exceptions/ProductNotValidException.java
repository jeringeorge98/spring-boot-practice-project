package com.example.practice.exceptions;



public class ProductNotValidException extends RuntimeException {
    public ProductNotValidException(String message) {
        super(message);
    }
}
