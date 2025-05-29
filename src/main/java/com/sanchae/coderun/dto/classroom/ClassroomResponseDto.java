package com.sanchae.coderun.dto.classroom;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
