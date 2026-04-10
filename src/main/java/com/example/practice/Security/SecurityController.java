package com.example.practice.Security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/special")
    public String getOpen(){
        return "Special Endpoint";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/basic")
    public String getClosed(){
        return "Basic Endpoint";
    }
}
