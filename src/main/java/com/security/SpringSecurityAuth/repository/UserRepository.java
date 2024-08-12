package com.security.SpringSecurityAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.SpringSecurityAuth.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
