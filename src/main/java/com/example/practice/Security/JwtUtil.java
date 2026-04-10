package com.example.practice.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey;

    private KeyStore keyStore;

    @Value("${jwt.keystore.location}")
    private String keyStoreLocation;
    @Value("${jwt.keystore.password}")
    private  String keyStorePasword;

    @Value(("${jwt.keystore.alias}"))
    private String keyStoreAlias;

    @PostConstruct
    private void init(){
        try{
            keyStore = KeyStore.getInstance("JKS");
            InputStream keyStoreStream = new ClassPathResource(keyStoreLocation.replace("classpath:","")).getInputStream();
            keyStore.load(keyStoreStream,keyStorePasword.toCharArray());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private PrivateKey getPrivateKey(){
        try{
            return (PrivateKey) keyStore.getKey(keyStoreAlias,keyStorePasword.toCharArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private PublicKey getPublicKey(){
        try{
            return keyStore.getCertificate(keyStoreAlias).getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(Authentication authentication){
        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusSeconds(1000*60*60*24)))
                .signWith(getPrivateKey())
                .compact()
                ;

    }
   
    private Claims extractAllClaims(String token) {
        // Extract claims after signature verification
        return Jwts
                .parser()
                .verifyWith(getPublicKey())  // Use public key for verification
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }
    public boolean validateToken(String jwt) {
        try {
            Jwts.parser()
                .verifyWith(getPublicKey())  // Use public key for verification
                .build()
                .parseSignedClaims(jwt);     // Modern method
            return !isTokenExpired(jwt);     // Also check expiration
        } catch (Exception e) {
            return false;
        }
    }
    
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
}
