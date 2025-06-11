package com.sanchae.coderun.classes.dto;

import lombok.Data;

@Data
public class ClassroomResponseDto {
    private Long id;
    private String name;
    private String description;

    public ClassroomResponseDto() {}

    public ClassroomResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
