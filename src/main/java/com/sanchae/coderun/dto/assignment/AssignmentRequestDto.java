package com.sanchae.coderun.dto.assignment;

public class AssignmentRequestDto {
    private String title;
    private String description;
    private String dueDate;
    private Long classroomId;

    public AssignmentRequestDto() {}

    public AssignmentRequestDto(String title, String description, String dueDate, Long classroomId) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.classroomId = classroomId;
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