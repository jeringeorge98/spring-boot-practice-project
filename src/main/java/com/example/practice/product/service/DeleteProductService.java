package com.example.practice.product.service;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Long, Optional<Product>> {

    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Optional<Product>> execute(Long input) {
        Optional<Product> optionalProduct = productRepository.findById(input);
        if(optionalProduct.isEmpty()){
            return  null;
        }
        productRepository.delete(optionalProduct.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(optionalProduct);
    }
}
