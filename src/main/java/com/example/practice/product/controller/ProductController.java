package com.example.practice.product.controller;


import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import com.example.practice.product.model.ProductDTO;
import com.example.practice.product.model.UpdateProductCommand;
import com.example.practice.product.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductController {

   private final CreateProductService createProductService;
   private final UpdateProductService updateProductService;
   private final DeleteProductService deleteProductService;
   private final GetProductService getProductService;
   private final SearchProductService searchProductService;
   private final GetSingleProductService getSingleProductService;
   // constructor injection
    public ProductController(CreateProductService createProductService, UpdateProductService updateProductService, DeleteProductService deleteProductService, GetProductService getProductService, SearchProductService searchProductService, GetSingleProductService getSingleProductService) {
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
        this.getSingleProductService = getSingleProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getProductService.execute(null);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> getProductByName(@RequestParam String name){
        return searchProductService.execute(name);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable Long id){
        return getSingleProductService.execute(id);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable Long id){
     return deleteProductService.execute(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product product){
     return updateProductService.execute(new UpdateProductCommand(id,product));
    }



}
