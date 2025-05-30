package com.example.application.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.application.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String user);

}
