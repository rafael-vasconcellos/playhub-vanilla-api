package com.rafael.playhub.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthService { 
    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication authenticate(String username, String pass) { 
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, pass);
        Authentication authentication = authenticationManager.authenticate(token);

        // Define a autenticação no contexto de segurança
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) { return authentication; }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to athenticate");
    }
}
