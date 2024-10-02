package com.rafael.playhub.production;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductionRepository extends JpaRepository<Production, UUID> {
    List<Production> findByNameContaining(String name);
    List<Production> findByTagsContaining(String tag);
    List<Production> findByStyleContaining(String style);
}