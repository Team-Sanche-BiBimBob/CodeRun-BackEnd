package com.sanchae.coderun.domain.user.dto.profile.response;

import com.sanchae.coderun.domain.language.entity.Language;
import lombok.Builder;
import lombok.Getter;

import java.io.File;

@Getter
@Builder
public class UserProfileResponseDto {

    private Long id;

    private Long userId;
    private String username;

    private String profileImage;

    private String userDescription;

    private Language recentlyStudiedLanguage;
    private Long recentlyStudiedLanguageProgress;
    private Long recentlyStudiedLanguageScore;

    private Language mostStudiedLanguage;
    private Long mostStudiedLanguageProgress;
    private Long mostStudiedLanguageScore;
}
