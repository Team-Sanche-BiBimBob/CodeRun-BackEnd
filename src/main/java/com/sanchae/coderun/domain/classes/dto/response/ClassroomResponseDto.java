package com.sanchae.coderun.domain.classes.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassroomResponseDto {
    private Long id;
    private String name;
    private String description;

    public ClassroomResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
