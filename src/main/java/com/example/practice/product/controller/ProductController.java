package com.example.practice.product.controller;


import com.example.practice.product.service.CreateProductService;
import com.example.practice.product.service.DeleteProductService;
import com.example.practice.product.service.GetProductService;
import com.example.practice.product.service.UpdateProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class ProductController {

   private final CreateProductService createProductService;
   private final UpdateProductService updateProductService;
   private final DeleteProductService deleteProductService;
   private final GetProductService getProductService;
   // constructor injection
    public ProductController(CreateProductService createProductService, UpdateProductService updateProductService, DeleteProductService deleteProductService, GetProductService getProductService) {
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
    }

    @GetMapping
    public ResponseEntity<String> getProducts(){
        return getProductService.execute(null);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(){
        return createProductService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(){
     return deleteProductService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(){
     return updateProductService.execute(null);
    }



}
