package com.example.practice.product.service;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import com.example.practice.product.model.ProductDTO;
import com.example.practice.product.model.UpdateProductCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand input) {
        Optional<Product> productOptional = productRepository.findById(input.getId());
        if(productOptional.isEmpty()) {
         return null;
        }
        Product product = input.getProduct();
        product.setId(input.getId());
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product));
    }
}
