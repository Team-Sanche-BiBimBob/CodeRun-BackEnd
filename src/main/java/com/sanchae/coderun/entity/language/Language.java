package com.sanchae.coderun.entity.language;

import jakarta.persistence.*;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // 예: Java, Python, JavaScript

    private String version; // 예: Java 17, Python 3.11

    public Language() {}

    public Language(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
}