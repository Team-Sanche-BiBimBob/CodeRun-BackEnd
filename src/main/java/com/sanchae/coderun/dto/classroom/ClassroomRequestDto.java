package com.sanchae.coderun.dto.classroom;


public class ClassroomRequestDto {
    private String name;
    private String description;

    public ClassroomRequestDto() {}

    public ClassroomRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}