package com.sanchae.coderun.domain.language.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LanguageResponseDto {
    private Long id;
    private String name;
    private String version;
    private String description;
}