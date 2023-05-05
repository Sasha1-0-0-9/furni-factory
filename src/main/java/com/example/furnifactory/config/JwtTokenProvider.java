package com.example.furnifactory.config;

import com.example.furnifactory.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenProvider {
    public String generateTokenForUser(User user) {

        // Create a claims object and add the user's role to it
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole().getType().name());
        claims.put("id", user.getId());

        // Create a key to sign the token
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

        // Build the token and return it as a string
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();

        return token;
    }
}
