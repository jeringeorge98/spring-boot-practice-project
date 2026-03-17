package com.example.practice.product;


import com.example.practice.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // need to preserve the naming condition findBy*ColumnName*Containing
    List<Product> findByNameContaining(String name);

    @Query("Select p from products WHERE p.name LIKE %keyword% OR p.description LIKE %keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String name);

}
