package com.example.practice.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productName")
    private String name;
    private Double price;
    @Column(name = "productDescription")
    private String description;
}
