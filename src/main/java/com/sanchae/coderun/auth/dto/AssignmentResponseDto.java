package com.sanchae.coderun.auth.dto;

import lombok.Data;

@Data
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
}