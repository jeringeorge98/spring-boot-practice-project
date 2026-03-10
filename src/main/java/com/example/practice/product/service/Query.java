package com.example.practice.product.service;

import org.springframework.http.ResponseEntity;


// read only services
public interface Query<I,O> {
    ResponseEntity<O> execute(I input);
}
