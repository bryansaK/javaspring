package com.example.application.service.user;

import com.example.application.enums.role.UserRole;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(String email, String password, UserRole roleName) {
        Role role = roleRepository.findByName(roleName.name());
        User user = new User(email, password, role);
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
            Role role = roleRepository.findByName(roleName.name());
            user.setRole(role);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(String email, String password, UserRole roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }

    @Override
    public void updateUser(Long id, String name, String email, String password, UserRole roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
    
}
