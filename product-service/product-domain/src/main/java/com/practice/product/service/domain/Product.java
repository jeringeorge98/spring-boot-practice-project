package com.practice.product.service.domain;

import com.practice.product.service.domain.valueObject.ProductID;
import lombok.Getter;

@Getter
public class Product extends AggregateRoot<ProductID> {

    private final String productName;
    private final Double price;
    public Product(String productName, Double price, String description) {
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    private final String description;
}
