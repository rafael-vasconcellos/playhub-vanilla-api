package com.rafael.playhub.production;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Production")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Production { 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "type")
    private String type;
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    private String tags;

    @Column(name = "style")
    private String style;

    @Column(name = "exclusive")
    private Boolean exclusive;

    @Column(name = "banner")
    private String banner;

    @Column(name = "large_banner")
    private String large_banner;

    @Column(name = "top10")
    private String top10;
}