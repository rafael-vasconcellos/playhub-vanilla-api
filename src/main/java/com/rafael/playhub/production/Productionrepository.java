package com.rafael.playhub.production;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface Productionrepository extends JpaRepository<Production, UUID> {
    List<Production> findByName(String name);
    List<Production> findByTag(String tag);
    List<Production> findByStyle(String style);
}