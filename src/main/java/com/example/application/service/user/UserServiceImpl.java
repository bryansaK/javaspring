package com.example.application.service.user;

import java.util.ArrayList;
import java.util.List;

import com.example.application.config.PasswordEncoderProvider;
import com.example.application.entity.Role;
import com.example.application.entity.User;
import com.example.application.enums.role.UserRole;
import com.example.application.repository.role.RoleRepository;
import com.example.application.repository.user.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderProvider passwordEncoderProvider = new PasswordEncoderProvider();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(String name, String email, String password, UserRole roleName) { //Use un DTO a la place
        List<Role> roles = roleRepository.findAllByName(new ArrayList<>(List.of(roleName)));
        String hashedPassword = passwordEncoderProvider.passwordEncoder().encode(password);
        User user = User.builder()
                .name(name)
                .email(email)
                .password(hashedPassword)
                .roles(roles)
                .build();
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(Long id, String name, String email, String password, UserRole roleName) {
        User user = findUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            List<Role> roles = roleRepository.findAllByName(new ArrayList<>(List.of(roleName)));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
