package com.sanchae.coderun.domain.classes.dto.request;

import lombok.Data;

@Data
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
}