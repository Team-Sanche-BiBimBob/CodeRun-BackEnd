package com.sanchae.coderun.domain.user.dto.profile.request;

import lombok.Getter;

import java.io.File;

@Getter
public class UpdateUserProfileRequestDto {
    private String profileImage;

    private String userDescription;
}
