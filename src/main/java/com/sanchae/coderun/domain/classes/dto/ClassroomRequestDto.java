package com.sanchae.coderun.domain.classes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassroomRequestDto {
    private String name;
    private String description;

    public ClassroomRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}