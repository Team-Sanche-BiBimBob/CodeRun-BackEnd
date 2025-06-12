package com.sanchae.coderun.language.dto;

import lombok.Data;

@Data
public class LanguageResponseDto {
    private Long id;
    private String name;
    private String version;

    public LanguageResponseDto() {}

    public LanguageResponseDto(Long id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }
}