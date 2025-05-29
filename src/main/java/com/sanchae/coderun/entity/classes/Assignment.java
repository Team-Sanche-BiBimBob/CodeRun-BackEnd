package com.sanchae.coderun.entity.classes;
import jakarta.persistence.*;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;        // 과제 제목
    private String description;  // 과제 설명
    private String dueDate;      // 마감일 (문자열 또는 LocalDateTime)

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classroom classRoom;  // 과제가 속한 수업

    public Assignment() {}

    public Assignment(String title, String description, String dueDate, Classroom classRoom) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.classRoom = classRoom;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDueDate() {
        return dueDate;
    }
    public Classroom getClassRoom() {
        return classRoom;
    }
}