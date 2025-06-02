package com.example.application.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.config.PasswordEncoderProvider;
import com.example.application.dto.user.UserDto;
import com.example.application.entity.Role;
import com.example.application.entity.User;
import com.example.application.enums.role.UserRole;
import com.example.application.repository.role.RoleRepository;
import com.example.application.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderProvider passwordEncoderProvider = new PasswordEncoderProvider();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(UserDto userDto) { //Use un DTO a la place
        User user = User.builder()
                .name(userDto.getFirstName())
                .email(userDto.getEmail())
                .password(passwordEncoderProvider.passwordEncoder().encode(userDto.getPassword()))
                .roles(roleRepository.findAllByRoleNameIn(new ArrayList<>(List.of(UserRole.USER))))
                .build();
        User result = userRepository.save(user);
        return result;
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
            List<Role> roles = roleRepository.findAllByRoleNameIn(new ArrayList<>(List.of(roleName)));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
