package com.practice.product.service.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateProductCommand {
private String productName;
private Double price;
private String description;

}
