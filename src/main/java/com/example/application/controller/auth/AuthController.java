package com.example.application.controller.auth;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.application.dto.user.UserDto;
import com.example.application.entity.User;
import com.example.application.service.user.UserServiceImpl;

@Controller
public class AuthController {

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public ResponseEntity<?> registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User savedUSer = userService.saveUser(userDto);
        return ResponseEntity.ok(savedUSer);
    }
}
