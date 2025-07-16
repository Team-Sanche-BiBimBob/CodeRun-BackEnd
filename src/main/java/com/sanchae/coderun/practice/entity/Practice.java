package com.sanchae.coderun.practice.entity;


import com.sanchae.coderun.language.entity.Language;
import com.sanchae.coderun.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.List;

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

    @OneToMany(mappedBy = "practice")
    private List<Problem> problems;

    public Practice() {}

    public Practice(String title, String description, String level) {
        this.title = title;
        this.description = description;
        this.level = level;
    }
}