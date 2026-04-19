package com.example.practice.Security;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@Log4j2
public class SecurityController {

    @PostMapping("/login")
    private ResponseEntity<Object> login(@RequestBody LoginRequest body) {
        try {
            if (body.getUserName().equals("admin") && body.getPassWord().equals("1234")) {
                String jwtToken = JwtUtil.generateToken(body.getUserName());
                return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(jwtToken));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        } catch (Exception e) {
            log.error("Error with {e}");
            throw new RuntimeException(e);
        }
    }
}
