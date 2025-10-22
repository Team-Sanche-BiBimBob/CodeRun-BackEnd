package com.sanchae.coderun.domain.user.repository;

import com.sanchae.coderun.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
