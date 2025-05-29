package com.sanchae.coderun.entity.classes;

import com.sanchae.coderun.entity.auth.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    public Classroom() {}

    public Classroom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Assignment> getAssignments() { return assignments; }
}