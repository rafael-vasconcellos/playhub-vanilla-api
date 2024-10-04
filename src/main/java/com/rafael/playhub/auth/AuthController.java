package com.rafael.playhub.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.playhub.auth.DTO.AuthResponse;


@RestController
@RequestMapping("/auth")
public class AuthController { 
    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<?> getLogin(@RequestParam Optional<String> username, @RequestParam Optional<String> pass) { 
        if (username.isPresent() && pass.isPresent()) { 
            Authentication authentication = authService.authenticate(username.get(), pass.get());
            return ResponseEntity.ok(new AuthResponse("Sucess!", authentication.getName()));
        }

        return ResponseEntity.badRequest().build();
    }

}

