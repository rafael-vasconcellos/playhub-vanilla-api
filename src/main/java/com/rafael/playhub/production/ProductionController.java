package com.rafael.playhub.production;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.playhub.production.DTO.ProductionSearchQuery;
import com.rafael.playhub.production.DTO.TagRequest;


@RestController
public class ProductionController { 
    @Autowired
    private ProductionRepository productionRepository;

    @GetMapping
    public ResponseEntity<String> hello() { return ResponseEntity.ok("Hello world!"); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduction(@PathVariable UUID id) { 
        Optional<Production> optional_prod = this.productionRepository.findById(id);
        if (optional_prod.isPresent()) { return ResponseEntity.ok(optional_prod.get()); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam ProductionSearchQuery query) { 
        return ResponseEntity.ok(this.productionRepository.findByNameContaining(query.name()));
    }

    //
}