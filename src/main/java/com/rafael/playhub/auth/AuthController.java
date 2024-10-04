package com.rafael.playhub.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.playhub.auth.DTO.AuthResponse;


@RestController
@RequestMapping("/auth")
public class AuthController { 
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ResponseEntity<?> getLogin(@RequestParam Optional<String> username, @RequestParam Optional<String> pass) { 
        if (username.isPresent() && pass.isPresent()) { 
            Authentication authentication = this.authService.authenticate(username.get(), pass.get());
            String token = this.tokenService.generateToken((UserDetails) authentication.getPrincipal());
            return ResponseEntity.ok(
                new AuthResponse("Sucess!", authentication.getName(), token)
            );
        }

        return ResponseEntity.badRequest().build();
    }

}

