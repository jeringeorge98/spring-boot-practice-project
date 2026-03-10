package com.example.practice.product.service;

import org.springframework.http.ResponseEntity;


// write services like CREATE,UPDATE
public interface Command<I,O> {

 ResponseEntity<O> execute(I input);
}
