package com.sanchae.coderun.domain.language.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LanguageRequestDto {
    private String name;
    private String version;
    private String description;
}