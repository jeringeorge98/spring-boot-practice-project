package com.example.practice.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
try {
    String token = getJwtToken(request);
    if(token!=null && JwtUtil.validateToken(token)){
       String username = JwtUtil.getUsernameFromToken(token);
       UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
    filterChain.doFilter(request,response);
} catch (Exception e) {
    filterChain.doFilter(request,response);
    throw new RuntimeException(e);
}

    }

    private String getJwtToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token.isBlank() || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7);

    }
}
