package com.sanchae.coderun.language.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // 예: Java, Python, JavaScript

    private String version; // 예: Java 17, Python 3.11

    @Builder
    public Language(String name, String version) {
        this.name = name;
        this.version = version;
    }
}