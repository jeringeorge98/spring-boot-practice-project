package com.example.practice.product.service;


import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import com.example.practice.product.model.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductPaginationService implements Query<Integer, Page<Product>> {

    private final ProductRepository productRepository;

    public GetProductPaginationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Page<Product>> execute(Integer input) {
        Pageable pageable = PageRequest.of(input - 1, 10);
        Page<Product> products = productRepository.findAll(pageable);
        return ResponseEntity.ok(products);
    }
}
