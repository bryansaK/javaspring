package com.example.application.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.application.entity.User;
import com.example.application.enums.role.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findUsersByRoles_RoleName(UserRole roleName);
}