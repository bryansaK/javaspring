package com.example.application.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderProvider {
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public boolean isPasswordSame(String passwordRequest, String password) {
        return passwordEncoder().matches(password, passwordEncoder().encode(passwordRequest));
    }
}
