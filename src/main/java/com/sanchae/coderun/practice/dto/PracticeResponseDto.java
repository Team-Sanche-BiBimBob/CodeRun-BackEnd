package com.sanchae.coderun.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeResponseDto {
    private Long id;
    private String title;
    private Long languageId;
}
