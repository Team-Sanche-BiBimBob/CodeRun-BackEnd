package com.sanchae.coderun.domain.user.service;

import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {
    public UserProfileResponseDto getUserProfile(Long id);
    public UpdateUserProfileResponseDto updateUserProfile(Long id, Long userId, UpdateUserProfileRequestDto updateUserProfileRequestDto);
}
