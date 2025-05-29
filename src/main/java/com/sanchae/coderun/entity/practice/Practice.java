package com.sanchae.coderun.entity.practice;

import jakarta.persistence.*;

@Entity
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String level;

    private String language;

    public Practice() {}

    public Practice(String title, String description, String level, String language) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.language = language;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLevel() { return level; }
    public String getLanguage() { return language; }
}