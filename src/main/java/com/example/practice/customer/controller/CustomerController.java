package com.example.practice.customer.controller;

import com.example.practice.customer.CustomerRepository;
import com.example.practice.customer.model.Customer;
import com.example.practice.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable  Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalCustomer.get());

    }
}
