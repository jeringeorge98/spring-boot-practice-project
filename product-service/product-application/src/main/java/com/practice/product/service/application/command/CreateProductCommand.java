package com.practice.product.service.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder
@AllArgsConstructor
public class CreateProductCommand {
  private UUID productId;
  private  String productName;
   private String description;
   private Double price;

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
