package com.rafael.playhub.production;


@RestController
public class ProductionController {
    @GetMapping
    public ResponseEntity<String> hello() { return ResponseEntity.ok("Hello world!"); }

    //
}