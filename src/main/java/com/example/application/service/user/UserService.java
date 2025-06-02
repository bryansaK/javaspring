package com.example.application.service.user;

import com.example.application.dto.user.UserDto;
import com.example.application.entity.User;
import com.example.application.enums.role.UserRole;

public interface UserService {
    User saveUser(UserDto userDto);
    User findUserByEmail(String email);
    User findUserById(Long id);
    void updateUser(Long id, String name, String email, String password, UserRole roleName);
    void deleteUser(Long id);
}
