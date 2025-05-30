package com.example.application.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.application.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findUsersByRoles_RoleName(String roleName);
}