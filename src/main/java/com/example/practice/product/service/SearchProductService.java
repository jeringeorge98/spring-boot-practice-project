package com.example.practice.product.service;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String,List<ProductDTO>> {

    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String input) {
        List<ProductDTO> productList = productRepository.findByNameContaining(input).stream().map(ProductDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}
