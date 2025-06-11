package com.sanchae.coderun.classes.dto;

import lombok.Data;

@Data
public class ClassroomRequestDto {
    private String name;
    private String description;

    public ClassroomRequestDto() {}

    public ClassroomRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}