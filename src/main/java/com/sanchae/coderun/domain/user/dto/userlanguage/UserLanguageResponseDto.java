package com.sanchae.coderun.domain.user.dto.userlanguage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLanguageResponseDto {
    private Long id;
    private Long userId;
    private Long languageId;
}