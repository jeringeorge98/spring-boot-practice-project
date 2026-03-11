package com.example.practice.product.service;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import com.example.practice.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductService implements Query<Void,List<ProductDTO>> {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(ProductDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }
}
