package com.example.practice.product.model;

import lombok.Data;

@Data
public class UpdateProductCommand {
    private Long id;

    public UpdateProductCommand(Long id, Product product) {
        this.id = id;
        this.product = product;
    }

    private Product product;
}
