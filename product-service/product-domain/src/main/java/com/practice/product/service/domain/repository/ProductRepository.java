package com.practice.product.service.domain.repository;

import com.practice.product.service.domain.Product;
import com.practice.product.service.domain.valueObject.ProductID;

import java.util.Optional;

public interface ProductRepository{
    Product save(Product product);
    Optional<Product> findProductFromId(ProductID id);

}
