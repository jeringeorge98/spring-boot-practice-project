package com.example.practice.product.service;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import com.example.practice.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product,ProductDTO>{

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product input) {
        try {
            productRepository.save(input);
            ProductDTO productDTO = new ProductDTO(input);
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
