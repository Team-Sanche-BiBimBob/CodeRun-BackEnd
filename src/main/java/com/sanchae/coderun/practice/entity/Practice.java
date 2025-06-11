package com.sanchae.coderun.practice.entity;


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

    @ManyToOne
    private PracticeSession practiceSession;

    public Practice() {}

    public Practice(String title, String description, String level, PracticeSession practiceSession) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.practiceSession = practiceSession;
    }
}