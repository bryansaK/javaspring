package com.example.application.repository.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.application.entity.Role;
import com.example.application.enums.role.UserRole;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByRoleNameIn(List<UserRole> roleNames);

}
