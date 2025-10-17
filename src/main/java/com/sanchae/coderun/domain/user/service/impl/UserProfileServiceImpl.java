package com.sanchae.coderun.domain.user.service.impl;

import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import com.sanchae.coderun.domain.user.entity.UserProfile;
import com.sanchae.coderun.domain.user.repository.UserProfileRepository;
import com.sanchae.coderun.domain.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileResponseDto getUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(()
                -> new RuntimeException("찾으시려는 사용자의 id가 없습니다."));

        return UserProfileResponseDto.builder()
                .id(userProfile.getId())
                .userId(userProfile.getUserId())
                .profileImage(userProfile.getProfileImage())
                .userDescription(userProfile.getUserDescription())
                .recentlyStudiedLanguage(userProfile.getRecentlyStudiedLanguage())
                .recentlyStudiedLanguageProgress(userProfile.getRecentlyStudiedLanguageProgress())
                .recentlyStudiedLanguageScore(userProfile.getRecentlyStudiedLanguageScore())
                .mostStudiedLanguage(userProfile.getMostStudiedLanguage())
                .mostStudiedLanguageProgress(userProfile.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(userProfile.getMostStudiedLanguageScore())
                .build();
    }

    @Override
    public UpdateUserProfileResponseDto updateUserProfile(Long userId, Long id, UpdateUserProfileRequestDto updateUserProfileRequestDto) {

        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);

        if (userProfile == null) {
            return null;
        }

        UserProfile savedUserProfile = UserProfile.builder()
                .id(id)
                .userId(userId)
                .profileImage(updateUserProfileRequestDto.getProfileImage())
                .userDescription(updateUserProfileRequestDto.getUserDescription())
                .recentlyStudiedLanguage(userProfile.getRecentlyStudiedLanguage())
                .recentlyStudiedLanguageProgress(userProfile.getRecentlyStudiedLanguageProgress())
                .recentlyStudiedLanguageScore(userProfile.getRecentlyStudiedLanguageScore())
                .mostStudiedLanguage(userProfile.getMostStudiedLanguage())
                .mostStudiedLanguageProgress(userProfile.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(userProfile.getMostStudiedLanguageScore())
                .build();

        userProfileRepository.save(savedUserProfile);

        return UpdateUserProfileResponseDto.builder()
                .userId(userProfile.getUserId())
                .userDescription(userProfile.getUserDescription())
                .build();
    }
}
