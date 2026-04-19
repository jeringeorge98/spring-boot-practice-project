package com.example.practice.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    // Generate a cryptographically secure key for HS512 (recommended)
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    private KeyStore keyStore;
    private static final long EXPIRATION_10_DAYS = 864_000_000;

    @Value("${jwt.keystore.location}")
    private String keyStoreLocation;
    @Value("${jwt.keystore.password}")
    private  String keyStorePasword;

    @Value(("${jwt.keystore.alias}"))
    private String keyStoreAlias;

//    @PostConstruct
//    private void init(){
//        try{
//            keyStore = KeyStore.getInstance("JKS");
//            InputStream keyStoreStream = new ClassPathResource(keyStoreLocation.replace("classpath:","")).getInputStream();
//            keyStore.load(keyStoreStream,keyStorePasword.toCharArray());
//        }
//        catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//    private PrivateKey getPrivateKey(){
//        try{
//            return (PrivateKey) keyStore.getKey(keyStoreAlias,keyStorePasword.toCharArray());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private PublicKey getPublicKey(){
//        try{
//            return keyStore.getCertificate(keyStoreAlias).getPublicKey();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public String generateToken(Authentication authentication){
//        return Jwts.builder()
//                .subject(authentication.getName())
//                .issuedAt(new Date())
//                .expiration(Date.from(Instant.now().plusSeconds(1000*60*60*24)))
//                .signWith(getPrivateKey())
//                .compact()
//                ;
//
//    }

    public static String  generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .signWith(key)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_10_DAYS ))
                .compact();
    }
    public static String getUsernameFromToken(String jwt){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload().getSubject();
    }
   
//    private Claims extractAllClaims(String token) {
//        // Extract claims after signature verification
//        return Jwts
//                .parser()
//                .verifyWith(getPublicKey())  // Use public key for verification
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//    public boolean isTokenExpired(String token) {
//        Claims claims = extractAllClaims(token);
//        return claims.getExpiration().before(new Date());
//    }
    public static boolean validateToken(String jwt) {
        try {
           Jwts.parser()
                   .verifyWith(key).build().parseSignedClaims(jwt);
           return true;
        } catch (ExpiredJwtException e) {
           log.info("Expired JWT");
        }
        catch (UnsupportedJwtException e){
            log.info("Unsupported Exception");
        }
        catch (io.jsonwebtoken.security.SecurityException e){
            log.info("JWT Exception,Invalid Token");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
