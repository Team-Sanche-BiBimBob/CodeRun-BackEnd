package com.sanchae.coderun.domain.user.service.impl;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.user.dto.profile.request.RenewalUserMostStudiedLanguageRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.request.RenewalUserRecentlyStudiedLanguageRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.RenewalUserMostStudiedLanguageResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.RenewalUserRecentlyStudiedLanguageResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.entity.UserProfile;
import com.sanchae.coderun.domain.user.repository.UserProfileRepository;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import com.sanchae.coderun.domain.user.service.UserProfileService;
import com.sanchae.coderun.global.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final Path root = Paths.get("uploads/profile");

    @Override
    public UserProfileResponseDto getUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾으시려는 사용자의 id가 없습니다."));

        User user = userRepository.findById(userProfile.getUserId()).orElse(null);

        return UserProfileResponseDto.builder()
                .id(userProfile.getId())
                .userId(userProfile.getUserId())
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
    public UpdateUserProfileResponseDto updateUserProfile(Long userId, UpdateUserProfileRequestDto updateUserProfileRequestDto) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId).orElse(null);
        if (userProfile == null) {
            return null;
        }

        UserProfile savedUserProfile = UserProfile.builder()
                .id(userProfile.getId())
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

        UserProfile savedUserProfile = userProfileRepository.save(newUserProfile);

        return UpdateUserProfileResponseDto.builder()
                .userId(savedUserProfile.getUserId())
                .userDescription(savedUserProfile.getUserDescription())
                .build();
    }

    @Override
    public String updateProfileImage(CustomUserDetails customUserDetails, MultipartFile file) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if (ext == null) ext = "jpg";
            String lower = ext.toLowerCase();

            Set<String> allow = Set.of("jpg", "jpeg", "png", "gif", "webp");
            if (!allow.contains(lower)) {
                throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다.");
            }

            Long userId = customUserDetails.getId();

            String filename = userId + "_profile." + lower;
            Path target = root.resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            UserProfile profile = userProfileRepository.findByUserId(userId)
                    .orElseGet(() -> {
                        UserProfile p = new UserProfile();
                        p.setUserId(userId);
                        return p;
                    });

            profile.setProfileImage(filename);
            userProfileRepository.save(profile);

            return "/uploads/profile/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 실패: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<byte[]> getProfileImage(Long userId) {
        try {
            UserProfile profile = userProfileRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다."));

            if (profile.getProfileImage() == null) {
                return ResponseEntity.notFound().build();
            }

            Path path = root.resolve(profile.getProfileImage());
            if (!Files.exists(path)) {
                return ResponseEntity.notFound().build();
            }

            byte[] bytes = Files.readAllBytes(path);
            String contentType = switch (FilenameUtils.getExtension(profile.getProfileImage()).toLowerCase()) {
                case "png" -> "image/png";
                case "gif" -> "image/gif";
                case "webp" -> "image/webp";
                default -> "image/jpeg";
            };

            return ResponseEntity.ok()
                    .header("Content-Type", contentType)
                    .body(bytes);
        } catch (IOException e) {
            throw new RuntimeException("이미지 로드 실패: " + e.getMessage());
        }
    }
}