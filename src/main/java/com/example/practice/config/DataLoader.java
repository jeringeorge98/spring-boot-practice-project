package com.example.practice.config;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    private final  ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Seeding data in product db...");
        if (productRepository.count() == 0) {
            seedProducts();
        }
    }

    private void seedProducts() {
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setPrice(999.99);
        product1.setDescription("High-performance laptop for work and gaming");

        Product product2 = new Product();
        product2.setName("Smartphone");
        product2.setPrice(599.99);
        product2.setDescription("Latest smartphone with advanced features");

        Product product3 = new Product();
        product3.setName("Headphones");
        product3.setPrice(199.99);
        product3.setDescription("Wireless noise-cancelling headphones");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        
        System.out.println("Database seeded with initial products");
    }
}