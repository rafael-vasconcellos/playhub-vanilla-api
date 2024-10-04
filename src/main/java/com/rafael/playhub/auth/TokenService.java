package com.rafael.playhub.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService { 
    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private UserDetailsService userDetailsService;


    public String generateToken(UserDetails user) throws JWTCreationException { 
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        String token = JWT.create()
            .withIssuer("auth-api")
            .withSubject(user.getUsername())
            .withExpiresAt(this.getExpirationDate())
            .sign(algorithm);
        
        return token;
    }

    public UsernamePasswordAuthenticationToken validateToken(String token) { 
        try { 
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            String username = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            UserDetails user = this.userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());

        } catch(JWTVerificationException e) { 
            System.out.println("\n\n" + e.getMessage());
            return null; 
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now()
                .plusMonths(1)
                .atZone(ZoneOffset.of("-03:00"))
                .toInstant();
    }
}
