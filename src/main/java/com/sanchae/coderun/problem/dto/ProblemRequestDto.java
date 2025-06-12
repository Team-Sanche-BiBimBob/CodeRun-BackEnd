package com.sanchae.coderun.problem.dto;

import lombok.Data;

@Data
public class ProblemRequestDto {
    private String title;
    private String description;
    private String level;
    private String language;

    public ProblemRequestDto() {}
    public ProblemRequestDto(String title, String description, String level, String language) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.language = language;
    }
}
