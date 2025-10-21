package com.sanchae.coderun.domain.classes.entity;

import com.sanchae.coderun.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    public Classroom() {}

    public Classroom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @OneToMany
    private List<ClassroomFolder> classroomFolder;
}