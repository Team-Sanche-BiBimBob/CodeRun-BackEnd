package com.sanchae.coderun.user.repository;

import com.sanchae.coderun.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    User findByEmail(String email);
}
