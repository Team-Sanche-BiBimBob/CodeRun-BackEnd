package com.sanchae.coderun.dto.assignment;

public class AssignmentResponseDto {
    private Long id;
    private String title;
    private String description;
    private String dueDate;
    private Long classroomId;

    public AssignmentResponseDto() {}

    public AssignmentResponseDto(Long id, String title, String description, String dueDate, Long classroomId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.classroomId = classroomId;
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

    public Long getClassroomId() {
        return classroomId;
    }
}