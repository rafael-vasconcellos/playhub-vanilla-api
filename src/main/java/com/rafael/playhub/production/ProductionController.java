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


@RestController
public class ProductionController { 
    @Autowired
    private ProductionRepository productionRepository;

    @GetMapping
    public ResponseEntity<String> hello() { return ResponseEntity.ok("Hello world!"); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduction(@PathVariable UUID id) { 
        System.out.println(id);
        Optional<Production> optional_prod = this.productionRepository.findById(id);
        if (optional_prod.isPresent()) { return ResponseEntity.ok(optional_prod.get()); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String name) { 
        //System.out.println(name);
        return ResponseEntity.ok(this.productionRepository.findByNameContaining(name));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam Optional<String> tags, @RequestParam Optional<String> style) { 
        List<Production> results = new ArrayList<>();
        if (tags.isPresent()) { results.addAll(this.productionRepository.findByTagsContaining(tags.get())); }
        if (style.isPresent()) { results.addAll(this.productionRepository.findByStyleContaining(style.get())); }
        if (tags.isPresent() && style.isPresent()) { 
            results = results.stream()
                             .filter( prod -> prod.getTags().toLowerCase().contains(tags.get()) && prod.getStyle().toLowerCase().contains(style.get()) )
                             .toList();
        }
        return ResponseEntity.ok(results);
    }

    //
}