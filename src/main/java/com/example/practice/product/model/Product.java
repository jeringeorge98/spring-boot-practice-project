package com.example.practice.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productName")
    @NotNull(message = "Name must not be null")
    private String name;
    @PositiveOrZero(message = "Price cannot be negative")
    private Double price;
    @Size(min = 20,message = "Minimum 20 charachters")
    @Column(name = "productDescription")
    private String description;
}
