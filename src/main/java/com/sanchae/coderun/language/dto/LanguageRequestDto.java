package com.sanchae.coderun.language.dto;

import lombok.Data;

@Data
public class LanguageRequestDto {
    private String name;
    private String version;

    public LanguageRequestDto() {}

    public LanguageRequestDto(String name, String version) {
        this.name = name;
        this.version = version;
    }
}