package com.example.practice.product;


import com.example.practice.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // need to preserve the naming condition findBy*ColumnName*Containing
    List<Product> findByNameContaining(String name);

}
