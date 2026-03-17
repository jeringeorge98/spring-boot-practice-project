package com.example.practice.product.service;

import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetSingleProductService implements Query<Long, ProductDTO>{

    private final ProductRepository productRepository;

    public GetSingleProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<ProductDTO> execute(Long input) {

     Optional<ProductDTO> productDTO = productRepository.findById(input).map(ProductDTO::new);
        return productDTO.map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseThrow(() -> new ProductNotFoundException("Product is not avaialbale for that id"));
    }
}
