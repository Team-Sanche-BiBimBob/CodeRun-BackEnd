package com.sanchae.coderun.domain.user.repository;

import com.sanchae.coderun.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
