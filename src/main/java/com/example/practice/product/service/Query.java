package com.example.practice.product.service;

import com.example.practice.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


// read only services
public interface Query<I,O> {
    ResponseEntity<List<ProductDTO>> execute(I input);
}
