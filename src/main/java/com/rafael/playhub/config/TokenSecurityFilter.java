package com.rafael.playhub.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rafael.playhub.auth.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class TokenSecurityFilter extends OncePerRequestFilter { 
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException { 

        UsernamePasswordAuthenticationToken token = this.retrieveToken(request);
        if (token != null) { 
            // Define a autenticação no contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken retrieveToken(HttpServletRequest request) { 
        var header = request.getHeader("Authorization");
        if (header == null) return null;
        String token = header.replace("Bearer ", "");
        return this.tokenService.validateToken(token);
    }
}
