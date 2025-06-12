package com.sanchae.coderun.practice.entity;


import com.sanchae.coderun.language.entity.Language;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String level;

    private PracticeType type;

    @ManyToOne
    private Language language;

    public Practice() {}

    public Practice(String title, String description, String level) {
        this.title = title;
        this.description = description;
        this.level = level;
    }
}